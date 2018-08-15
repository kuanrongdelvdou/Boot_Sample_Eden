package com.eden.hao.service;

import java.util.List;

import com.eden.hao.entity.Dept;

public interface DeptService {

	public boolean add(Dept dept);

	public Dept get(Long id);

	public List<Dept> list();
}
