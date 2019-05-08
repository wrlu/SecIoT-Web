package com.wrlus.seciot.traffic.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrlus.seciot.pysocket.PyClient;
import com.wrlus.seciot.pysocket.model.PySocketRequest;
import com.wrlus.seciot.pysocket.model.PySocketResponse;
import com.wrlus.seciot.traffic.model.ConnectionDetails;
import com.wrlus.seciot.util.exception.PythonException;
import com.wrlus.seciot.util.exception.PythonIOException;
import com.wrlus.seciot.util.exception.PythonRuntimeException;

@Deprecated
@Service
public class TrafficServiceImpl implements TrafficService {
	@SuppressWarnings("unused")
	private static Logger log = LogManager.getLogger();
	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public ConnectionDetails getConnectionDetails(File pcapFile, String ip) throws PythonException {
		PySocketRequest request = new PySocketRequest();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("file_path", pcapFile.getAbsolutePath());
		parameters.put("ip_addr", ip);
		request.setCmd("TrafficService.get_connection_details");
		request.setParameters(parameters);
		PyClient pyClient = new PyClient();
		pyClient.connect();
		PySocketResponse result = pyClient.sendCmdSync(request);
		if (result.getStatus() == 0) {
			try {
				ConnectionDetails details = mapper.readValue(mapper.writeValueAsString(result.getData()), ConnectionDetails.class);
				return details;
			} catch (Exception e) {
				throw new PythonIOException("An error occured when parsing response from python server.", e);
			}
		} else {
			throw new PythonRuntimeException("Python出现异常，错误代码："+result.getStatus());
		}
	}

}
