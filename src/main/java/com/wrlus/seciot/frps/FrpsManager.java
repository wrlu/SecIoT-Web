package com.wrlus.seciot.frps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wrlus.seciot.util.os.OSUtil;

public class FrpsManager {
	private static Logger log = LogManager.getLogger();
	private Process process;
	private Thread frpsThread;
	
	public void init() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		if (OSUtil.isWindows()) {
			path = path.replace("file:/", "");
		} else {
			path = path.replace("file:", "");
		}
		path = path.replace("classes/", "frps-"+OSUtil.getArch()+"/");
		if (OSUtil.isWindows()) {
			path = OSUtil.escapeUnixSeparator(path);
		}
		String frpsStartScript = path + "startfrps.sh";
		ProcessBuilder processBuilder = new ProcessBuilder("sh", frpsStartScript);
		processBuilder.redirectErrorStream(true);
		try {
			process = processBuilder.start();
			log.info("Starting Frps Daemon...");
			BufferedReader bs = new BufferedReader(new InputStreamReader(process.getInputStream()));
			frpsThread = new Thread(()->{
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
                log.warn("Stopping Frps Daemon...");
			});
			frpsThread.setDaemon(true);
			frpsThread.setName("Thread-Frps");
	        frpsThread.start();
		} catch (IOException e) {
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
	}
	
	public void destory() {
		log.warn("Stopping Frps Daemon...");
		frpsThread.interrupt();
		process.destroy();
	}
}
