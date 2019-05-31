package com.service.system.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.encrypt.Encrypt;
import com.model.SessionUser;
import com.model.system.SysUser;
import com.service.impl.BaseManager;
import com.service.system.LoginService;

/**  
 *  
 * @author:wangzhen
 * @version:V1.0
 * 2017年4月26日  
 */
@Service
public class LoginManager extends BaseManager implements LoginService{

	@Autowired
	protected Encrypt encryptByMD5;
	@Autowired
	protected SessionUser sessionUser;
	@Override
	public SessionUser login(String loginName, String passWord) {
		//使用md5加密算法
		passWord = encryptByMD5.encrypt(passWord);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("loginName", loginName);
		map.put("loginPwd", passWord);
		SysUser sysUser = (SysUser) getUniqueByMap(SysUser.class, map);
		if(sysUser!=null){
			sessionUser.setUser(sysUser);
			return sessionUser;
		}else {
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SysUser> getUserList(Object... para){
		// TODO Auto-generated method stub
		List<SysUser> userlist = (List<SysUser>) this.getListByHql("select sysVal from SysUser sysVal where sysVal.userId = ?", para);
		log.info(userlist.size()+"");
		log.info(userlist.size());
		for (int i = 0; i < userlist.size(); i++) {
			SysUser user = userlist.get(i);
			log.info(userlist.get(i).toString());
			log.info(user.getUserId());
		}
		return userlist;
	}

	@Override
	public SysUser getLoginUser(String loginName) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("loginName", loginName);
		SysUser sysUser = (SysUser) getUniqueByMap(SysUser.class, map);
		return sysUser;
	}
	

}
