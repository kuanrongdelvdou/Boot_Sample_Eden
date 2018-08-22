package com.eden.hao.cache.employeeController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.eden.hao.cache.bean.Employee;
import com.eden.hao.cache.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/emp/{id}")
	public Employee getEmp(@PathVariable("id") Integer id){
		
		Employee emp = employeeService.getEmp(id);
		
		return emp;
	}
}
