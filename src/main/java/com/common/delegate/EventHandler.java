/**
 *
 * EventHandler.java
 * 2019年1月23日
 * author:Lynn
 */
package com.common.delegate;


/**
 * 委托列添加事件
 * @author Lynn
 * 2019年1月23日
 */
public class EventHandler {
	public static String addEvent(Object object,String methodName,Object ...para){
		Event event = new Event(object,methodName,para);
		String valueString = event.invoke();
		return valueString;
	}
}
