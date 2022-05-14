package com.example.springProject;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

@Component
public class InjectStringAnnotationBeanPostProcessor implements BeanPostProcessor {
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

		for (Field field : bean.getClass().getDeclaredFields()) {
			InjectString annotation = field.getAnnotation(InjectString.class);

			if (annotation != null) {
				field.setAccessible(true);
				ReflectionUtils.setField(field, bean, annotation.s());
			}
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}
}
