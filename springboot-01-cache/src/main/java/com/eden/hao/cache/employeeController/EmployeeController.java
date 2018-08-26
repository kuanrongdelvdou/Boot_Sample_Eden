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
	
	// 更新新员工数据
	@GetMapping("/emp")
	public Employee update(Employee emp){

		employeeService.updateEmp(emp);
		return emp;
	}
	

	@GetMapping("/delemp")
	public String deleteEmp(Integer id){
		employeeService.deleteEmp(id); 
		return "successfully delete emp, id is : " + id;
	}
	
	@GetMapping("/emp/lastname/{lastName}")
	public Employee getEmployeeByLastName(@PathVariable("lastName") String lastName){
		return employeeService.getEmployeeByLastName(lastName);
	}
	
}
