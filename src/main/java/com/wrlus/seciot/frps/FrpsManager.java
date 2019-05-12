package com.wrlus.seciot.frps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wrlus.seciot.util.os.OSUtil;

public class FrpsManager {
	private static Logger log = LogManager.getLogger();
	private Thread frpsThread;
	
	public void init() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		if (OSUtil.isWindows()) {
			path = path.replace("file:/", "");
		} else {
			path = path.replace("file:", "");
		}
		path = path.replace("classes/", "frp/");
		if (OSUtil.isWindows()) {
			path = OSUtil.escapeUnixSeparator(path);
		}
		String frps_name = path + "frps";
		String param = "-c";
		String frps_ini_nameString = path + "frps.ini";
		
		frpsThread = new Thread(new FrpsHandler(frps_name, param, frps_ini_nameString));
		frpsThread.setName("Thread-frps");
		frpsThread.setDaemon(true);
		frpsThread.start();
	}
	
	public void destory() {
		frpsThread.interrupt();
	}
	
	class FrpsHandler implements Runnable {
		private String frps_name, param, frps_ini_name;
		
		public FrpsHandler(String frps_name, String param, String frps_ini_name) {
			this.frps_name = frps_name;
			this.param = param;
			this.frps_ini_name = frps_ini_name;
		}
		
		@Override
		public void run() {
			ProcessBuilder processBuilder = new ProcessBuilder(frps_name, param, frps_ini_name);
			processBuilder.redirectErrorStream(true);
			try {
				Process process = processBuilder.start();
				BufferedReader bs = new BufferedReader(new InputStreamReader(process.getInputStream()));
				log.info("Starting frps Daemon...");
				log.debug("FrpsName: "+frps_name);
				log.debug("Frps config file (frps.ini): "+frps_ini_name);
				try {
					process.waitFor();
				} catch (InterruptedException e) {
					process.destroy();
					log.warn("Stoping frps Daemon...");
				}
				String line = null;
				while ((line = bs.readLine()) != null) {
				    System.out.println(line);
				}
				log.warn("frps Daemon exited with code "+process.exitValue());
			} catch (IOException e) {
				if (log.isDebugEnabled()) {
					e.printStackTrace();
				}
			}
		}
	}
}
