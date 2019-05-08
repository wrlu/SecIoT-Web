package com.wrlus.seciot.pysocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrlus.seciot.pysocket.model.PySocketRequest;
import com.wrlus.seciot.pysocket.model.PySocketResponse;
import com.wrlus.seciot.util.exception.PythonException;
import com.wrlus.seciot.util.exception.PythonIOException;

public class PyClient {
	private Socket socket;
	private BufferedWriter writer;
	private BufferedReader reader;
	private static Logger log = LogManager.getLogger();

	public void connect() throws PythonIOException {
		if (socket == null || !socket.isConnected()) {
			try {
				socket = new Socket("localhost", 8888);
				OutputStream os = socket.getOutputStream();
				InputStream is = socket.getInputStream();
				writer = new BufferedWriter(new OutputStreamWriter(os));
				reader = new BufferedReader(new InputStreamReader(is));
				log.debug("Connect to server "+socket.getInetAddress().getHostAddress()+":"+socket.getPort());
			} catch (IOException e) {
				throw new PythonIOException("An error occured when connecting to python server.", e);
			}
		}
	}
	
	public void close() throws IOException {
		if (socket != null && !socket.isClosed()) {
			socket.close();
		}
	}
	
	public PySocketResponse sendCmdSync(PySocketRequest cmd) throws PythonException {
		return sendCmd(cmd);
	}
	
	public void sendCmdAsync(PySocketRequest cmd, Callback callback) {
		Thread pyCmdThread = new Thread(()->{
			try {
				PySocketResponse response = sendCmd(cmd);
				callback.onSuccess(response);
			} catch (PythonException e) {
				PySocketResponse response = new PySocketResponse();
				response.setStatus(-1);
				response.setReason(e.getLocalizedMessage());
				callback.onError(response);
				log.error(e.getClass().getName() + ": " + e.getLocalizedMessage());
				if (log.isDebugEnabled()) {
					e.printStackTrace();
				}
			}
		});
		pyCmdThread.setName("PyClient SendCmdAsync Thread");
		pyCmdThread.setDaemon(true);
		pyCmdThread.start(); 
	}
	
	private PySocketResponse sendCmd(PySocketRequest request) throws PythonException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String data = mapper.writeValueAsString(request);
			log.debug("Send request to the server: "+data);
			writer.write(data);
			writer.flush();
			String receiveData = reader.readLine();
			PySocketResponse response = mapper.readValue(receiveData, PySocketResponse.class);
			return response;
		} catch (Exception e) {
			throw new PythonIOException("An error occured when sending command to python server.", e);
		}
	}
	
	public void sendExitSignal(int signal) throws PythonException {
		PySocketRequest request = new PySocketRequest();
		Map<String, Object> params = new HashMap<>();
		params.put("code", 1);
		request.setCmd("exit");
		request.setParameters(params);
		ObjectMapper mapper = new ObjectMapper();
		try {
			String data = mapper.writeValueAsString(request);
			log.debug("Send request to the server: "+data);
			log.debug("Send exit signal to the server: "+signal);
			writer.write(data);
			writer.flush();
		} catch (Exception e) {
			throw new PythonIOException("An error occured when sending exit signal to python server.", e);
		}
	}
}