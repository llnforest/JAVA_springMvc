package com.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.common.log.SysLogger;
import com.common.spring.BeanHelper;
import com.model.system.SysDictValue;
import com.service.system.DictService;

/**  
 * 系统字典工具类
 * @author:wangzhen
 * @version:V1.0
 * 2018年3月15日  
 */
public class DictUtil {
	
	//系统字典数据map集合(code+valCode:valName)
	private static HashMap<String,String> dictMap=new HashMap<String,String>();
	private static HashMap<String,String> dictColorMap=new HashMap<String,String>();
	//记录当前字典编码是否已经加入到缓存中(code:true)
	private static HashMap<String,Boolean> dictCache=new HashMap<String,Boolean>();
	private static HashMap<String,Boolean> dictColorCache=new HashMap<String,Boolean>();
	//系统字典清单集合
	private static HashMap<String,List<SysDictValue>> dictList=new HashMap<String,List<SysDictValue>>();
	private static HashMap<String,List<SysDictValue>> dictColorList=new HashMap<String,List<SysDictValue>>();
	
	
	/**
	 * 如果dictCode没有记录到缓存中，则去数据库中加载并将dictCode记录到缓存表中
	 * 不管是否加载到，只要记录到缓存表中，则直接从dictMap获取编码，获取不到则返回空
	 * 2018年3月15日
	 * @param dictCode
	 * author:wangzhen
	 */
	public static void loadDictMap(String dictCode){
		DictService dictManager =(DictService)BeanHelper.getBean("dictManager");
		List<SysDictValue> data =  dictManager.getDictMap(dictCode);
		if(CollectionUtils.isNotEmpty(data)){
			for (int i = 0; i < data.size(); i++) {
				SysDictValue dictValue =  data.get(i);
				if(dictValue!=null){
					dictMap.put(dictCode+"-"+dictValue.getValCode(), dictValue.getValName());
				}
			}
		}
		dictCache.put(dictCode, true);
		dictList.put(dictCode, data);
	}
	
	/**
	 * 如果dictCode没有记录到缓存中，则去数据库中加载并将dictCode记录到缓存表中(附加颜色)
	 * 不管是否加载到，只要记录到缓存表中，则直接从dictMap获取编码，获取不到则返回空
	 * 2018年3月15日
	 * @param dictCode
	 * author:wangzhen
	 */
	public static void loadDictMapColor(String dictCode){
		DictService dictManager =(DictService)BeanHelper.getBean("dictManager");
		List<SysDictValue> data =  dictManager.getDictMap(dictCode);
		if(CollectionUtils.isNotEmpty(data)){
			for (int i = 0; i < data.size(); i++) {
				SysDictValue dictValue =  data.get(i);
				if(dictValue!=null){
					String color = dictValue.getValColor();
					if(StringUtils.isNotEmpty(color)){
						dictColorMap.put(dictCode+"-"+dictValue.getValCode(), "<span style='color:"+color+"'>"+dictValue.getValName()+"</span>");
					}else{
						dictColorMap.put(dictCode+"-"+dictValue.getValCode(), dictValue.getValName());

					}
				}
			}
		}
		dictColorCache.put(dictCode, true);
		dictColorList.put(dictCode, data);
	}
	
	/**
	 * 该方法是根据传入的字典编码和字典值获取当前字典值对应的字典名称
	 * 2018年3月15日
	 * @param dictCode
	 * @param dictValue
	 * @return 字典对应的值
	 * author:wangzhen
	 */
	public static String getDictName(String dictCode,String dictValue){
		if(dictCache.get(dictCode)==null||!dictCache.get(dictCode)){
			loadDictMap(dictCode);
		}
		String dictName = dictMap.get(dictCode+"-"+dictValue);
		if(StringUtils.isNotEmpty(dictName)){
			return dictName;
		}else{
			return dictValue;
		}
	}
	
	/**
	 * 该方法是根据传入的字典编码和字典值获取当前字典值对应的字典名称(附加名称颜色)
	 * 2018年3月15日
	 * @param dictCode
	 * @param dictValue
	 * @return 字典对应的值
	 * author:wangzhen
	 */
	public static String getDictNameColor(String dictCode,String dictValue){
		if(dictColorCache.get(dictCode)==null||!dictColorCache.get(dictCode)){
			loadDictMapColor(dictCode);
		}
		String dictName = dictColorMap.get(dictCode+"-"+dictValue);
		if(StringUtils.isNotEmpty(dictName)){
			return dictName;
		}else{
			return dictValue;
		}
	}
	
	/**
	 * 该方法是根据传入的字典编码获取字典列表
	 * 2018年3月15日
	 * @param dictCode
	 * @return 字典列表
	 * author:wangzhen
	 */
	public static List<SysDictValue> getDictList(String dictCode){
		if(dictCache.get(dictCode)==null||!dictCache.get(dictCode)){
			loadDictMap(dictCode);
		}
		List<SysDictValue> data = dictList.get(dictCode);
		if(CollectionUtils.isNotEmpty(data)){
			return data;
		}else{
			return null;
		}
	}
	
	/**
	 * 该方法是根据传入的hql语句获取字典列表
	 * 2018年3月15日
	 * @param hql
	 * @return 字典列表
	 * author:wangzhen
	 */
	public static List<SysDictValue> getDictListByHql(String hql){
		try {
			DictService dictManager =(DictService)BeanHelper.getBean("dictManager");
			List<?> list = dictManager.getListByHql(hql);
			List<SysDictValue> data = new ArrayList<SysDictValue>();
			if(CollectionUtils.isNotEmpty(list)){
				for (int i = 0; i < list.size(); i++) {
					Object[] dictData = (Object[])list.get(i);
					SysDictValue sysDictValue = new SysDictValue();
					sysDictValue.setValName(dictData[0].toString());
					sysDictValue.setValCode(dictData[1].toString());
					data.add(sysDictValue);
				}
			}
		   
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 情况缓存中的字典数据
	 * 2018年9月10日
	 * author:wangzhen
	 */
	public static void clearDictCache(){
		 dictMap=new HashMap<String,String>();
		 dictColorMap=new HashMap<String,String>();
		 dictCache=new HashMap<String,Boolean>();
		 dictColorCache=new HashMap<String,Boolean>();
		 dictList=new HashMap<String,List<SysDictValue>>();
		 dictColorList=new HashMap<String,List<SysDictValue>>();
	}

}
