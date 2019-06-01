package com.wrlus.seciot.pysocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wrlus.seciot.util.os.OSUtil;

public class PyServerManager {
	private static Logger log = LogManager.getLogger();
	private Process process;
	private Thread readThread;
	
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
		log.debug("ScriptName: "+fullScriptName);
		ProcessBuilder processBuilder = new ProcessBuilder("python3", fullScriptName);
		processBuilder.redirectErrorStream(true);
		try {
			process = processBuilder.start();
			log.info("Starting Python Socket Server Daemon...");
			BufferedReader bs = new BufferedReader(new InputStreamReader(process.getInputStream()));
			readThread = new Thread(()->{
				try {
                    while(!Thread.currentThread().isInterrupted()) {
                        String line;
                        while ((line = bs.readLine()) != null) {
                            if (log.isDebugEnabled()) {
								System.out.println(line);
							}
                        }
                        Thread.sleep(1000);
                    }
                } catch (IOException | InterruptedException e) {
                    if (log.isDebugEnabled()) {
                    	e.printStackTrace();
					}
                }
                log.warn("Stopping Python Socket Server Daemon...");
			});
	        readThread.setDaemon(true);
	        readThread.setName("Thread-PySocketServer");
	        readThread.start();
		} catch (IOException e) {
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
	}
	
	public void destory() {
		log.warn("Stoping Python Socket Server Daemon...");
		process.destroy();
		readThread.interrupt();
	}
}
