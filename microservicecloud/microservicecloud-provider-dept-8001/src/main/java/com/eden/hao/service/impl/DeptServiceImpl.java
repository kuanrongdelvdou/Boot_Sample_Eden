package com.eden.hao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eden.hao.dao.DeptDao;
import com.eden.hao.entity.Dept;
import com.eden.hao.service.DeptService;

@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	DeptDao deptDao;
	
	@Override
	public boolean add(Dept dept) {
		
		return deptDao.addDept(dept);
	}

	@Override
	public Dept get(Long id) {
		
		return deptDao.findById(id);
	}

	@Override
	public List<Dept> list() {
		
		return deptDao.findAll();
	}

}
