/**
 *
 * KcxxService.java
 * 2019年3月7日
 * author:Lynn
 */
package com.service.w2;

import java.util.ArrayList;
import java.util.Map;

import com.service.BaseService;

public interface RecordsService extends BaseService {
	public String getWrongReason(String codes);
	
	public Map<String, Object> getSqlMapByFlow(String ksbh,String ksrq);
	
	public Map<Integer, ArrayList<Integer>> getPrintImages(Integer id);
}
