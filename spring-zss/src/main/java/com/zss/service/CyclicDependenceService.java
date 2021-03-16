package com.zss.service;

import com.zss.dao.IndexDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("cyclicService")
public class CyclicDependenceService {

	/**
	 * 测试循环依赖问题
	 */
	@Autowired
	private IndexDao indexDao;
}
