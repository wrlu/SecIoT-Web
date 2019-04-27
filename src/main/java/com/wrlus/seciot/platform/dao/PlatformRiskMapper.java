package com.wrlus.seciot.platform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.wrlus.seciot.platform.model.PlatformRiskDao;

@Repository
public interface PlatformRiskMapper {
	@Select("select * from platform_risk;")
	public List<PlatformRiskDao> getPlatformRiskAll();
	
	@Select("select * from platform_risk where id = #{id};")
	public List<PlatformRiskDao> getPlatformRiskById(@Param("id") String id);
	
	@Select("select * from platform_risk where id in (select id from platform_risk_category where category = #{category});")
	public List<PlatformRiskDao> getPlatformRiskByCategory(@Param("category") String category);

}
