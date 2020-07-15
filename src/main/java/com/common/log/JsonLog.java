/**
 *
 * JsonLog.java
 * 2019年6月26日
 * author:Lynn
 */
package com.common.log;

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.endpoint.ClientImpl.EchoContext;

public class JsonLog {
	//---------------系统管理------------
	public static final String SysMenu = "{'head':{'menuId':'菜单ID'},'body':{'parentId':'上级菜单ID','menuType':'菜单类型','menuTitle':'菜单名称','menuUrl':'菜单URL','businessType':'业务类别','buttonType':'按钮类型','buttonFunction':'按钮事件','logLevel':'日志级别','menuOrder':'菜单排序','remark':'菜单备注'}}";
	
	//---------------业务管理------------
	//考车信息
	public static final String W2Kcxx = "{'head':{'id':'车辆ID'},'body':{'kch':'考车编号','cph':'考车牌号','kcmc':'考车型号','kscx':'准考车型','zt':'车辆状态','xmxh':'项目序号','czip':'车载视频IP','cuser':'车载用户名','cpwd':'车载密码','zxip':'中心视频IP','zuser':'中心用户名','zpwd':'中心密码','fourip':'四合一视频IP','fouruser':'四合一用户名','fourpwd':'四合一密码'}}";

	public static String get(String a){
		Map<String, String> map = new HashMap<String, String>();
		map.put("com.model.w2.W2Kcxx",W2Kcxx);
		map.put("com.model.system.SysMenu",SysMenu);
		return map.get(a);
	}
}
