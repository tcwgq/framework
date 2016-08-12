package com.tcwgq.custom;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

import com.tcwgq.annotation.TcwgqResource;
import com.tcwgq.bean.BeanDefinition;
import com.tcwgq.bean.PropertyDefinition;

public class TcwgqClassPathXmlApplicationContext {
	private List<BeanDefinition> beanDefines = new ArrayList<BeanDefinition>();
	private Map<String, Object> singletons = new HashMap<String, Object>();

	public TcwgqClassPathXmlApplicationContext(String filename) {
		this.reamXML(filename);
		this.instanceBean();
		this.injectObject();
		this.annotationInject();
	}

	private void annotationInject() {
		for (String beanName : singletons.keySet()) {
			Object bean = singletons.get(beanName);
			if (bean != null) {
				try {
					PropertyDescriptor[] ps = Introspector.getBeanInfo(
							bean.getClass()).getPropertyDescriptors();
					for (PropertyDescriptor properdesc : ps) {
						Method setter = properdesc.getWriteMethod();// 获取属性的setter方法
						if (setter != null
								&& setter
										.isAnnotationPresent(TcwgqResource.class)) {
							TcwgqResource resource = setter
									.getAnnotation(TcwgqResource.class);
							Object value = null;
							if (resource.name() != null
									&& !"".equals(resource.name())) {
								value = singletons.get(resource.name());
							} else {
								value = singletons.get(properdesc.getName());
								if (value == null) {
									for (String key : singletons.keySet()) {
										// 判定此 Class 对象所表示的类或接口与指定的 Class
										// 参数所表示的类或接口是否相同，或是否是其超类或超接口。
										if (properdesc.getPropertyType()
												.isAssignableFrom(
														singletons.get(key)
																.getClass())) {
											value = singletons.get(key);
											break;
										}
									}
								}
							}
							setter.setAccessible(true);
							setter.invoke(bean, value);// 把引用对象注入到属性
						}
					}
					Field[] fields = bean.getClass().getDeclaredFields();
					for (Field field : fields) {
						if (field.isAnnotationPresent(TcwgqResource.class)) {
							TcwgqResource resource = field
									.getAnnotation(TcwgqResource.class);
							Object value = null;
							if (resource.name() != null
									&& !"".equals(resource.name())) {
								value = singletons.get(resource.name());
							} else {
								value = singletons.get(field.getName());
								if (value == null) {
									for (String key : singletons.keySet()) {
										if (field.getType().isAssignableFrom(
												singletons.get(key).getClass())) {
											value = singletons.get(key);
											break;
										}
									}
								}
							}
							field.setAccessible(true);// 允许访问private字段
							field.set(bean, value);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void injectObject() {
		for (BeanDefinition beanDefinition : beanDefines) {
			Object bean = singletons.get(beanDefinition.getId());
			if (bean != null) {
				try {
					PropertyDescriptor[] ps = Introspector.getBeanInfo(
							bean.getClass()).getPropertyDescriptors();
					for (PropertyDefinition propertyDefinition : beanDefinition
							.getProperties()) {
						for (PropertyDescriptor properdesc : ps) {
							if (propertyDefinition.getName().equals(
									properdesc.getName())) {
								Method setter = properdesc.getWriteMethod();// 获取属性的setter方法
																			// ,private
								if (setter != null) {
									Object value = null;
									if (propertyDefinition.getRef() != null
											&& !"".equals(propertyDefinition
													.getRef().trim())) {
										value = singletons
												.get(propertyDefinition
														.getRef());
									} else {
										value = ConvertUtils.convert(
												propertyDefinition.getValue(),
												properdesc.getPropertyType());
									}
									setter.setAccessible(true);
									setter.invoke(bean, value);// 把引用对象注入到属性
								}
								break;
							}
						}
					}
				} catch (Exception e) {
				}
			}
		}
	}

	private void instanceBean() {
		for (BeanDefinition beanDefinition : beanDefines) {
			try {
				if (beanDefinition.getClassName() != null
						&& !beanDefinition.getClassName().trim().equals(""))
					singletons.put(beanDefinition.getId(),
							Class.forName(beanDefinition.getClassName())
									.newInstance());
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	private void reamXML(String filename) {
		SAXReader saxReader = new SAXReader();
		Document document = null;
		try {
			URL xmlpath = this.getClass().getClassLoader()
					.getResource(filename);
			document = saxReader.read(xmlpath);
			Map<String, String> nsMap = new HashMap<String, String>();
			nsMap.put("ns", "http://www.springframework.org/schema/beans");// 加入命名空间
			XPath xsub = document.createXPath("//ns:beans/ns:bean");// 创建beans/bean查询路径
			xsub.setNamespaceURIs(nsMap);// 设置命名空间
			List<Element> beans = xsub.selectNodes(document);// 获取文档下所有bean节点
			for (Element element : beans) {
				String id = element.attributeValue("id");// 获取id属性值
				String clazz = element.attributeValue("class"); // 获取class属性值
				BeanDefinition beanDefine = new BeanDefinition(id, clazz);
				XPath propertysub = element.createXPath("ns:property");
				propertysub.setNamespaceURIs(nsMap);// 设置命名空间
				List<Element> propertys = propertysub.selectNodes(element);
				for (Element property : propertys) {
					String propertyName = property.attributeValue("name");
					String propertyref = property.attributeValue("ref");
					String propertyValue = property.attributeValue("value");
					PropertyDefinition propertyDefinition = new PropertyDefinition(
							propertyName, propertyref, propertyValue);
					beanDefine.getProperties().add(propertyDefinition);
				}
				beanDefines.add(beanDefine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object getBean(String beanName) {
		return singletons.get(beanName);
	}

}
