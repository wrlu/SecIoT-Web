package com.wrlus.seciot.protocol.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.wrlus.seciot.protocol.model.ProtocolDao;
import com.wrlus.seciot.protocol.model.ProtocolRiskDao;

@Repository
public interface ProtocolMapper {
	
	@Select("select * from protocol;")
	public List<ProtocolDao> getProtocolAll();
	@Select("select * from protocol where name = #{name};")
	public List<ProtocolDao> getProtocolByName(@Param("name") String name);
	@Select("select * from protocol where layer = #{layer};")
	public List<ProtocolDao> getProtocolByLayer(@Param("layer") int layer);
	@Select("select * from protocol_risk;")
	public List<ProtocolRiskDao> getProtocolRiskAll();
	@Select("select * from protocol_risk where id = #{id};")
	public List<ProtocolRiskDao> getProtocolRiskById(@Param("id") String id);
	@Select("select * from protocol_risk where protocol = #{protocolName};")
	public List<ProtocolRiskDao> getProtocolRiskByProtocol(@Param("protocolName") String protocolName);
	
}
