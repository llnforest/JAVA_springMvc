/**
 *
 * Event.java
 * 2019年1月23日
 * author:Lynn
 */
package com.common.delegate;

import java.lang.reflect.Method; 

/**
 * list列表委托列内容
 * @author Lynn
 */
public class Event {
	//要执行方法的对象 
	 private Object object; 
	 //要执行的方法名称 
	 private String methodName; 
	 //要执行方法的参数 
	 private Object[] params; 
	 //要执行方法的参数类型 
	 private Class[] paramTypes; 
	 
	 public Event(){ 
	 
	 } 
	 public Event(Object object,String methodName,Object ...args){ 
		  this.object=object; 
		  this.methodName=methodName; 
		  this.params=args; 
		  contractParamTypes(this.params); 
	 } 
	 
	 //根据参数数组生成参数类型数组 
	 private void contractParamTypes(Object[] params){ 
		  this.paramTypes = new Class[params.length]; 
		  for(int i=0;i<params.length;i++){ 
			  if(params[i] == null) params[i] = String.valueOf(params[i]);
			  this.paramTypes[i] = params[i].getClass(); 
		  } 
	 } 
	 
	 public Object getObject() { 
		 return object; 
	 } 
	 
	 //执行该 对象的该方法 
	 public String invoke(){
		 try{
			 Method method = object.getClass().getMethod(this.methodName,this.paramTypes); 
			 if(null==method){ 
				 return "--"; 
			 } 
			 return String.valueOf(method.invoke(this.getObject(), this.params)); 
		 }catch(Exception e){
			 return "--";
		 }
	 }
}
