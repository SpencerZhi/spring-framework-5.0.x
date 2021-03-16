package com.zss.dao.impl;

import com.zss.dao.IndexDao;
import com.zss.service.CyclicDependenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("indexDao")
public class IndexDaoImpl implements IndexDao {

	@Autowired
	private CyclicDependenceService cyclicDependenceService;

	public IndexDaoImpl(){
		System.out.println("indexDaoImpl -- init");
	}
}
