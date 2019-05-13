package com.wrlus.seciot.frps;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wrlus.seciot.util.exception.ClientAlreadyExistsException;
import com.wrlus.seciot.util.exception.NoSuchClientException;
import com.wrlus.seciot.util.exception.PortRunOutException;

public class FrpsPortManager {
	private static Logger log = LogManager.getLogger();
	private Map<String, Integer> portMap;
	private int[] portPool;
	private static int minPort = 9000, maxPort = 9999;
	
	public void init() {
		portMap = new HashMap<>();
		portPool = new int[maxPort - minPort + 1];
		for (int i = 0; i < portPool.length; i++) {
			portPool[i] = 0;
		}
		log.debug("Create FrpsPortManager instance.");
	}
	
	public int bindPort(String clientId) throws ClientAlreadyExistsException, PortRunOutException {
		if (portMap.containsKey(clientId)) {
			throw new ClientAlreadyExistsException();
		}
		int port = findAvaliablePort();
		if (port == 0) {
			throw new PortRunOutException("There is no avaliable port from "+minPort+" to "+maxPort+" on this server (Maybe too many client connections).");
		}
		portMap.put(clientId, port);
		log.info("Bind port "+port+" for client: "+clientId);
		return port;
	}
	
	public int findAvaliablePort() {
		synchronized (portPool) {
			for (int i = 0; i < portPool.length; i++) {
				if (portPool[i] == 0) {
					portPool[i] = 1;
					return minPort + i;
				}
			}
			return 0;
		}
	}
	
	public int getBindPort(String clientId) throws NoSuchClientException {
		if (portMap.containsKey(clientId)) {
			return portMap.get(clientId);
		} else {
			throw new NoSuchClientException("No such client with client id: "+clientId);
		}
	}
	
	public void unBindPort(String clientId) throws NoSuchClientException {
		if (portMap.containsKey(clientId)) {
			portPool[portMap.get(clientId) - minPort] = 0;
			portMap.remove(clientId);
		} else {
			throw new NoSuchClientException("No such client with client id: "+clientId);
		}
	}
	
	public void destory() {
		log.debug("Destory FrpsPortManager instance.");
	}
}
