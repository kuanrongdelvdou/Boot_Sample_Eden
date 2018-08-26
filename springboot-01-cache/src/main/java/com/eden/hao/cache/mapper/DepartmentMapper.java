package com.eden.hao.cache.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.eden.hao.cache.bean.Department;

@Mapper
public interface DepartmentMapper {

	@Select("SELECT * FROM department WHERE id = #{id}")
	public Department getDeptById(Integer id);
}
