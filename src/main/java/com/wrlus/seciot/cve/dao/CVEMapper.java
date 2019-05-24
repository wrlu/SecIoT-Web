package com.wrlus.seciot.cve.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.wrlus.seciot.cve.model.CVEDao;

@Repository
public interface CVEMapper {
	
	@Select("select * from cve;")
	public List<CVEDao> getCVEAll();
	
	@Select("select * from cve where cve_num = #{cve_num};")
	public List<CVEDao> getCVEByNum(@Param("cve_num") String cvenumber);
	
}
