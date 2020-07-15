/**
 *
 * EhcacheUtil.java
 * 2019年2月26日
 * author:Lynn
 */
package com.common.utils;

import java.net.URL;

import org.apache.commons.lang.StringUtils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhcacheUtil {
	private static Cache cache;
	private static String configfile="ehcache-setting.xml";
	
	public static void init(){
		if(cache == null){
//			URL url = EhcacheUtil.class.getResource(configfile);
			CacheManager cacheManager = CacheManager.create(EhcacheUtil.class.getClassLoader().getResourceAsStream(configfile));
			cache = cacheManager.getCache("userLogin");
		}
	}
	/**
     * 将数据存入Cache
     * @param cachename Cache名称
     * @param key 类似redis的Key
     * @param value 类似redis的value，value可以是任何对象、数据类型，比如person,map,list等
     */
    public static void put(String key, Object value, int liveSeconds,int idleSeconds){
    	init();
        Element ele = new Element(key, value);
        if(liveSeconds != 0) {//失效时的间隔时间 和最后一次操作时间间隔
        	ele.setTimeToLive(liveSeconds);
        }
        if(idleSeconds != 0) {//失效时的间隔时间 和最后一次操作时间间隔
        	ele.setTimeToIdle(idleSeconds);
        }
        cache.put(ele);
    }
 
    /**
     * 获取缓存cachename中key对应的value
     * @param cachename
     * @param key
     * @return
     */
    public static Element get(String key){
    	init();
        try {
            Element e = cache.get(key);
            if(e == null){
                return null;
            }
            return e;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (CacheException e) {
            e.printStackTrace();
        }
        return null;
    }
 
    /**
     * 清除缓存名称为cachename的缓存
     * @param cachename
     */
    public static void clearCache(){
    	init();
        try {
        	cache.removeAll();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 判断缓存是否存在    
     * @throws
     */
    public static boolean exist(String key){
    	init();
    	if(get(key) != null){
    		return true;
    	}
    	return false;
    }
 
    /**
     * 移除缓存cachename中key对应的value
     * @param cachename
     * @param key
     * @return 
     */
    public static boolean remove(String key){
    	if(StringUtils.isEmpty(key)) return true;
    	init();
    	boolean flag = cache.remove(key);
        cache.flush();
        return flag;
    }
    
    /**
     * 使缓存在某个时间过期   
     * @throws
     */
    public static boolean expire(String key,int liveTime, int timeout){
    	boolean boo = false;
    	Element el = get(key); 
    	if(el != null){
    		Object value = el.getObjectValue();
    		put(key, value,liveTime,timeout);
    		boo = true;
    	}
    	return boo;
    }


}
