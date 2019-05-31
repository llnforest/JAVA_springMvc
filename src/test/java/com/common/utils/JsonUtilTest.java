package com.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;

import com.common.log.SysLogger;
import com.model.system.SysUser;

/**  
 *  
 * @author:wangzhen
 * @version:V1.0
 * 2017年9月8日  
 */
public class JsonUtilTest {
	SysLogger log = new SysLogger(this);
	@Test
	@SuppressWarnings("all")
	public void object2JsonTest(){
		/****对象转json***/
		SysUser user = new SysUser();
		user.setUserId("1");
		user.setUserName("张三");
		String string = JsonUtil.object2Json(user);
		log.info(string);
		
		/****List转json***/
		List list = new ArrayList<SysUser>();
		SysUser user2 = new SysUser();
		user2.setUserId("3");
		user2.setUserName("王五");
		list.add(user);
		list.add(user2);
		String stringL = JsonUtil.object2Json(list);
		log.info(stringL);
		
		/****Map转json***/
		Map map = new HashMap<String, Object>();
		map.put("user1", user);
		map.put("user2", user2);
		String stringLM= JsonUtil.object2Json(map);
		log.info(stringLM);
		
	}
	
//	@Test(timeout = 1)
	@Test
	@SuppressWarnings("all")
	public void json2ObjectTest(){
		
		/****json转对象***/
		
		String json = "{\"userId\":\"2\",\"userName\":\"李四\"}";
		SysUser user = JsonUtil.json2Ojbect(json,SysUser.class );
		log.info(user.getUserName());
		
		/****json转列表***/
		List list = new ArrayList<SysUser>();
		String jsonList = "[{\"userId\":\"2\",\"userName\":\"李四\"},{\"userId\":\"9\",\"userName\":\"赵六\"}]";
		list = (List)JsonUtil.json2Ojbect(jsonList, List.class);
		log.info(list.get(0)+"");
	}
	
	@Test
	@SuppressWarnings("all")
	public void str2JSONObjectTest(){
		
		/****字符串转json对象***/
		String json = "{\"userId\":\"2\",\"userName\":\"张三\"}";
		JSONObject jsonObject = JsonUtil.str2JSONObject(json);
		
		log.info(jsonObject.getString("userName"));
		Assert.assertEquals("张三", jsonObject.getString("userName"));
		
	}
	
}
