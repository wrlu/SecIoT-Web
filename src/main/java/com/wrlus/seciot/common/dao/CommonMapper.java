package com.wrlus.seciot.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.wrlus.seciot.common.model.CVEDao;
import com.wrlus.seciot.common.model.PlatformRiskDao;
import com.wrlus.seciot.common.model.ThirdLibraryDao;

@Repository
public interface CommonMapper {
	
	@Select("select * from cve;")
	public List<CVEDao> getCVEAll();
	
	@Select("select * from cve where cve_num = #{cve_num};")
	public List<CVEDao> getCVEByNum(@Param("cve_num") String cvenumber);
	
	@Select("select * from cve where cve_num in (select cve_num from cve_category where category = #{category});")
	public List<CVEDao> getCVEByCategory(@Param("category") String category);
	
	@Select("select * from platform_risk;")
	public List<PlatformRiskDao> getPlatformRiskAll();
	
	@Select("select * from platform_risk where id = #{id};")
	public List<PlatformRiskDao> getPlatformRiskById(@Param("id") long id);
	
	@Select("select * from platform_risk where id in (select id from platform_risk_category where category = #{category});")
	public List<PlatformRiskDao> getPlatformRiskByCategory(@Param("category") String category);
	
	@Select("select * from third_library;")
	public List<ThirdLibraryDao> getThirdLibraryAll();
	
	@Select("select * from third_library where id = #{id};")
	public List<ThirdLibraryDao> getThirdLibraryById(@Param("id") long id);
}
