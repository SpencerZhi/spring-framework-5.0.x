package com.spring.imports;

import com.spring.aop.AopDemo;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.function.Predicate;


public class MyImportSelector implements ImportSelector {

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		System.out.println("MyImport selectImports");
		return new String[]{AopDemo.class.getName()};
	}

/*	@Override
	public Predicate<String> getExclusionFilter() {
		return null;
	}*/
}
