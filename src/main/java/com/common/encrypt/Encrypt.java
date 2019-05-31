package com.common.encrypt;
/**  
 *  
 * @author:wangzhen
 * @version:V1.0
 * 2016年9月5日  
 */
public interface Encrypt {
	
	 /**
	  * 将传入的明文字符串加密后返回密文字符串
	  * 2017年9月5日
	  * @param str 原明文字符串
	  * @return
	  * author:wangzhen
	  */
	 public String encrypt(String str);
	 /**
	  * 将传入的密文字符串解密后返回明文字符串
	  * 2017年9月5日
	  * @param str 加密字符串
	  * @return
	  * author:wangzhen
	  */
	 public String decrypt(String str);

}
