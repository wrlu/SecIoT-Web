package com.wrlus.seciot.pysocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wrlus.seciot.util.exception.PythonException;
import com.wrlus.seciot.util.os.OSUtil;

public class PyServerManager {
	private static Logger log = LogManager.getLogger();
	private Thread pySocketServerThread;
	
	public void init() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		if (OSUtil.isWindows()) {
			path = path.replace("file:/", "");
		} else {
			path = path.replace("file:", "");
		}
		path = path.replace("classes/", "python/");
		if (OSUtil.isWindows()) {
			path = OSUtil.escapeUnixSeparator(path);
		}
		String fullScriptName = path + "socket_server.py";
		pySocketServerThread = new Thread(new PySocketServerHandler(fullScriptName));
		pySocketServerThread.setName("Thread-PySocketServer");
		pySocketServerThread.setDaemon(true);
		pySocketServerThread.start();
	}
	
	public void destory() {
		PyClient client = new PyClient();
		log.warn("Stoping Python Socket Server Daemon...");
		try {
			client.connect();
			client.sendExitSignal(1);
			log.warn("Python Socket Server Daemon exited with code 1");
			client.close();
		} catch (PythonException | IOException e) {
			log.error("Cannot stop Python Socket Server: "+e.getLocalizedMessage());
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		pySocketServerThread.interrupt();
	}
	
	class PySocketServerHandler implements Runnable {
		private String fullScriptName;
		
		public PySocketServerHandler(String fullScriptName) {
			this.fullScriptName = fullScriptName;
		}
		
		@Override
		public void run() {
			ProcessBuilder processBuilder = new ProcessBuilder("python", fullScriptName);
			processBuilder.redirectErrorStream(true);
			try {
				Process process = processBuilder.start();
				BufferedReader bs = new BufferedReader(new InputStreamReader(process.getInputStream()));
				log.info("Starting Python Socket Server Daemon...");
				log.debug("ScriptName: "+fullScriptName);
				try {
					process.waitFor();
				} catch (InterruptedException e) {
					process.destroy();
					log.warn("Stoping Python Socket Server Daemon...");
				}
				String line = null;
				while ((line = bs.readLine()) != null) {
				    System.out.println(line);
				}
				log.warn("Python Socket Server Daemon exited with code "+process.exitValue());
			} catch (IOException e) {
				if (log.isDebugEnabled()) {
					e.printStackTrace();
				}
			}
		}
	}
}
