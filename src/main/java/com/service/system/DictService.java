package com.service.system;

import java.util.List;

import com.model.system.SysDict;
import com.model.system.SysDictValue;
import com.service.BaseService;



/**  
 *  
 * @author:wangzhen
 * @version:V1.0
 * 2017年4月27日  
 */
public interface DictService extends BaseService{

	/**
	 * 该方法用于获取系统中字典数据并将字典数据转换成map
	 * 例如：性别（sex）、字典值1：表示男，0：表示女
	 * 则转换成map的数据为：key（sex-1）/value（男）；：key（sex-0）/value（女）
	 * 2018年5月31日
	 * @param dictCode 字典编码，如果该参数为空，则加载全部字典
	 * @return
	 * author:wangzhen
	 */
	public List<SysDictValue> getDictMap(String dictCode);
	
	public List<SysDict> getDictList(Object ...para);
}
