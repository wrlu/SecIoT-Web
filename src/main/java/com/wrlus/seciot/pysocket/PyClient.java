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
import com.wrlus.seciot.util.Status;

public class PyClient {
	private Socket socket;
	private BufferedWriter writer;
	private BufferedReader reader;
	private static Logger log = LogManager.getLogger();

	public void connect() throws IOException {
		if (socket == null || !socket.isConnected()) {
			socket = new Socket("localhost", 8888);
			OutputStream os = socket.getOutputStream();
			InputStream is = socket.getInputStream();
			writer = new BufferedWriter(new OutputStreamWriter(os));
			reader = new BufferedReader(new InputStreamReader(is));
			log.debug("Connect to server "+socket.getInetAddress().getHostAddress()+":"+socket.getPort());
		}
	}
	
	public void close() throws IOException {
		if (socket != null && !socket.isClosed()) {
			socket.close();
		}
	}
	
	public PySocketResponse sendCmdSync(PySocketRequest cmd) throws IOException {
		return sendCmd(cmd);
	}
	
	public void sendCmdAsync(PySocketRequest cmd, PySocketListener callback) {
		Thread pyCmdThread = new Thread(()->{
			try {
				PySocketResponse response = sendCmd(cmd);
				callback.onSuccess(response);
			} catch (IOException e) {
				PySocketResponse response = new PySocketResponse();
				response.setStatus(Status.PY_ERROR);
				response.setReason("Python出现异常，错误代码："+Status.PY_ERROR);
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
	
	public void sendExitSignal(int signal) throws IOException {
		PySocketRequest request = new PySocketRequest();
		Map<String, Object> params = new HashMap<>();
		params.put("code", 1);
		request.setCmd("exit");
		request.setParameters(params);
		ObjectMapper mapper = new ObjectMapper();
		String data = mapper.writeValueAsString(request);
		log.debug("Send request to the server: "+data);
		log.debug("Send exit signal to the server: "+signal);
		writer.write(data);
		writer.flush();
	}
	
	private PySocketResponse sendCmd(PySocketRequest request) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String data = mapper.writeValueAsString(request);
		log.debug("Send request to the server: "+data);
		writer.write(data);
		writer.flush();
		String receiveData = reader.readLine();
		PySocketResponse response = mapper.readValue(receiveData, PySocketResponse.class);
		return response;
	}
}