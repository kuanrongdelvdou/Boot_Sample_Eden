package com.eden.hao.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eden.hao.cache.bean.Employee;
import com.eden.hao.cache.mapper.EmployeeMapper;

@Service
public class EmployeeService {

	@Autowired
	EmployeeMapper employeeMapper;
	
	public Employee getEmp(Integer id){
		
		System.out.println("查询" + id + "号员工");
		Employee emp = employeeMapper.getEmpById(id);
		return emp;
	}
}
