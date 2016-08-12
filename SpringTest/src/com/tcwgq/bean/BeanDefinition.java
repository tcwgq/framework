package com.tcwgq.bean;

import java.util.ArrayList;
import java.util.List;

public class BeanDefinition {
	private String id;
	private String className;
	private List<PropertyDefinition> properties = new ArrayList<PropertyDefinition>();

	public BeanDefinition() {

	}

	public BeanDefinition(String id, String className) {
		super();
		this.id = id;
		this.className = className;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<PropertyDefinition> getProperties() {
		return properties;
	}

	public void setProperties(List<PropertyDefinition> properties) {
		this.properties = properties;
	}

}
