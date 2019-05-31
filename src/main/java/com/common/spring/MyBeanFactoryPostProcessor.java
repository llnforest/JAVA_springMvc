package com.common.spring;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**  
 * 将所有名称以Action结尾的bean的scope默认设置为PROTOTYPE
 * 1.即每次请求都会重新生成一个action对象
 * 2.避免每次都要再action加@Scope("prototype") 
 * 3.如果已经设置了@Scope()的值，则以设置的优先
 * 4.@Scope可选值有singleton;prototype;request;session;global session
 * 5.model也要增加@Scope("prototype") 属性
 * @author:wangzhen
 * @version:V1.0
 * 2017年4月26日  
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)throws BeansException {
		String[] beanNames = beanFactory.getBeanDefinitionNames();
		for (String beanName : beanNames) {
			BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
			String beanClassName = bd.getBeanClassName();
		
			if (beanClassName != null && (beanClassName.endsWith("Action")||beanClassName.contains("com.model."))) {
				String scope = bd.getScope();
				if (BeanDefinition.SCOPE_SINGLETON.equals(scope) || StringUtils.isBlank(scope)) {
					bd.setScope(BeanDefinition.SCOPE_PROTOTYPE);
				}
			}
		}
	}
}
