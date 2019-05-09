package com.wrlus.seciot.agent.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.wrlus.seciot.pysocket.PyClient;
import com.wrlus.seciot.pysocket.model.PySocketRequest;
import com.wrlus.seciot.pysocket.model.PySocketResponse;
import com.wrlus.seciot.util.exception.PythonException;
import com.wrlus.seciot.util.exception.PythonIOException;
import com.wrlus.seciot.util.exception.PythonRuntimeException;

@Service
public class AgentAndroidServiceImpl implements AgentAndroidService {
	private static Logger log = LogManager.getLogger();
//	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public String getFridaVersion() throws PythonException {
		PySocketRequest request = new PySocketRequest();
		Map<String, Object> parameters = new HashMap<>();
		request.setCmd("FridaService.get_frida_version");
		request.setParameters(parameters);
		PyClient pyClient = new PyClient();
		pyClient.connect();
		PySocketResponse response = pyClient.sendCmdSync(request);
		log.debug(response.toString());
		try {
			pyClient.close();
		} catch (IOException e) {
			throw new PythonIOException("An error occured when parsing response from python server.", e);
		}
		if (response.getStatus() == 0) {
			String version = String.valueOf(response.getData().get("version"));
			return version;
		} else {
			throw new PythonRuntimeException();
		}
	}

}
