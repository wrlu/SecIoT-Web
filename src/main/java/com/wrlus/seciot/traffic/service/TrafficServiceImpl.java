package com.wrlus.seciot.traffic.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrlus.seciot.pysocket.PyClient;
import com.wrlus.seciot.pysocket.model.PySocketRequest;
import com.wrlus.seciot.pysocket.model.PySocketResponse;
import com.wrlus.seciot.pysocket.model.PythonException;
import com.wrlus.seciot.traffic.model.ConnectionDetails;
import com.wrlus.seciot.util.Status;

@Service
public class TrafficServiceImpl implements TrafficService {

	@Override
	public ConnectionDetails getConnectionDetails(File pcapFile, String ip) throws IOException, PythonException{
		PySocketRequest request = new PySocketRequest();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("file_path", pcapFile.getAbsolutePath());
		parameters.put("ip_addr", ip);
		request.setCmd("TrafficService.get_connection_details");
		request.setParameters(parameters);
		PyClient pyClient = new PyClient();
		pyClient.connect();
		PySocketResponse result = pyClient.sendCmdSync(request);
		if (result.getStatus() == Status.SUCCESS) {
			ObjectMapper mapper = new ObjectMapper();
			ConnectionDetails details = mapper.readValue(mapper.writeValueAsString(result.getData()), ConnectionDetails.class);
			return details;
		} else {
			throw new PythonException("Python出现异常，错误代码："+result.getStatus());
		}
	}

}
