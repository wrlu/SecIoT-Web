package com.wrlus.seciot.library.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wrlus.seciot.library.model.ThirdLibraryDao;
import com.wrlus.seciot.library.model.ThirdLibraryRiskDao;

public interface ThirdLibraryMapper {
	@Select("select * from third_library;")
	public List<ThirdLibraryDao> getThirdLibraryAll();
	
	@Select("select * from third_library where id = #{id};")
	public List<ThirdLibraryDao> getThirdLibraryById(@Param("id") String id);
	
	@Select("select * from library_risk where name = #{libname} and version = #{libver};")
	public List<ThirdLibraryRiskDao> getThirdLibraryRiskByLibInfo(@Param("libname") String libname, @Param("libver") String libver);
	
	@Select("select * from library_risk where id = #{id};")
	public List<ThirdLibraryRiskDao> getThirdLibraryRiskById(String id);
}
