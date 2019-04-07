package com.wrlus.seciot.pysocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrlus.seciot.util.Status;

public class PySocket {
	private Socket socket;
	private BufferedWriter writer;
	private BufferedReader reader;
	private static Logger log = LogManager.getLogger();

	public void connect() throws IOException {
		if (!socket.isConnected()) {
			socket = new Socket("localhost", 8888);
			OutputStream os = socket.getOutputStream();
			InputStream is = socket.getInputStream();
			writer = new BufferedWriter(new OutputStreamWriter(os));
			reader = new BufferedReader(new InputStreamReader(is));
			log.debug("Connect to server "+socket.getInetAddress().getHostAddress()+":"+socket.getPort());
		}
	}
	
	public void close() throws IOException {
		if (!socket.isClosed()) {
			socket.close();
		}
	}
	
	public PySocketResponse sendCmdSync(PySocketRequest cmd) {
		try {
			return sendCmd(cmd);
		} catch (IOException e) {
			PySocketResponse response = new PySocketResponse();
			response.setStatus(Status.PY_ERROR);
			response.setReason("Python出现异常，错误代码："+Status.PY_ERROR);
			log.error(e.getClass().getName() + ": " + e.getLocalizedMessage());
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			return response;
		}
	}
	
	public void sendCmdAsync(PySocketRequest cmd, PySocketListener callback) {
		Thread pyCmdThread = new Thread(()->{
			try {
				PySocketResponse resultModel = sendCmd(cmd);
				callback.onSuccess(resultModel);
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
		pyCmdThread.setName("PythonCmdThread");
		pyCmdThread.start(); 
	}
	
	private PySocketResponse sendCmd(PySocketRequest cmd) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String data = mapper.writeValueAsString(cmd);
		log.debug("Send cmd to the server: "+data);
		writer.write(data);
		writer.flush();
		PySocketResponse response = mapper.readValue(reader, PySocketResponse.class);
		return response;
	}
}