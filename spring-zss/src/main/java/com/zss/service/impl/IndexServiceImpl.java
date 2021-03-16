package com.zss.service.impl;

import com.zss.service.IndexService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service("indexService")
public class IndexServiceImpl implements IndexService {

	Class clazz;

	public IndexServiceImpl(Class clazz){
		this.clazz = clazz;
	}

	@PostConstruct
	public void init(){
		System.out.println("Hello Spring!");
	}

	@Override
	public void print() {
		System.out.println(this.clazz);
	}
}
