package com.common.encrypt.impl;


import org.springframework.stereotype.Component;

import com.common.encrypt.Encrypt;
import com.common.log.SysLogger;

/**
 * 
 * @author:wangzhen
 * @version:V1.0 2016年9月5日
 */
@Component
public class EncryptByDES implements Encrypt {

	SysLogger log = new SysLogger(this);

	@Override
	public String encrypt(String str) {
		return null;
	}

	@Override
	public String decrypt(String str) {
		return null;
	}

}
