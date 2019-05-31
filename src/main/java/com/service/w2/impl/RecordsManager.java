/**
 *
 * KcxxManager.java
 * 2019年3月7日
 * author:Lynn
 */
package com.service.w2.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.common.utils.Const;
import com.common.utils.Search;
import com.model.w2.W2Records;
import com.service.impl.BasePkNumManager;
import com.service.w2.KcxxService;
import com.service.w2.RecordsService;

@Service
public class RecordsManager extends BasePkNumManager implements RecordsService {
	private int nextInt;

	@Override
	public Map<String, Object> getSqlMap(ModelMap map){
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("select obj.id,obj.ksbh as 考生编号,obj.xm as 考生姓名,obj.zjhm as 证件号码,obj.zkzh as 准考证号,obj.ksjg as 考试结果,obj.kcbh as 车号,obj.kscx as 车型,obj.ksyy as 考试原因,obj.yycs as 预约次数,obj.kscs as 考试次数,obj.ksrq as 考试日期,obj.kssj1 as 开始时间1,obj.jssj1 as 结束时间1,obj.kfxx1 as 扣分信息1,obj.jgfs1 as 分数1,obj.ksy1 as 考官1,obj.kssj2 as 开始时间2,obj.jssj2 as 结束时间2,obj.kfxx2 as 扣分信息2,obj.jgfs2 as 分数2,obj.ksy2 as 考官2,obj.sfprint as 打印 ,obj.jxdm as 驾校代码,obj.jxmc as 驾校名称,obj.sjjyw as 校验码");
		hql.append(" from W2Records obj  where 1=1  ");
		if(map.get("kg") != null && StringUtils.isNotEmpty(map.get("kg").toString())){
			hql.append(" and (obj.ksy1 like '%"+map.get("kg").toString()+"%' or obj.ksy2 like '%"+map.get("kg").toString()+"%') ");
		}
		if(map.get("kscj") != null && StringUtils.isNotEmpty(map.get("kscj").toString())){
			hql.append(" and obj.ksjg in ("+map.get("kscj").toString()+") ");
		}
		Map<String, Object> where = Search.where(hql, map);
		hql = (StringBuffer) where.get("hql");
		hql = Search.getOrder(hql,"obj.ksbh");
		sqlMap.put(Const.PAGE_SQL, hql.toString());
		sqlMap.put(Const.SQL_PARA, where.get("para"));
		return sqlMap;
		
	}

	@Override
	public String getWrongReason(String codes) {
		if(StringUtils.isEmpty(codes) || codes.equals("null")) return "";
		if(codes.substring(codes.length()-1).equals(";")) codes = codes.substring(0,codes.length()-1);
		codes = codes.replace(";", ",");
		log.info(codes);
		List<?> list = this.getListByHql("select dm.name,obj.gakfdm,obj.kf,obj.kfmc from W2Ksxmkfdm obj,W2Ksxmdm dm where 1=1 and obj.ksxmdm = dm.id and obj.gakfdm in ("+codes+")");
		StringBuffer strBuffer = new StringBuffer();
		if(CollectionUtils.isNotEmpty(list)){
			for(int i = 0; i < list.size();i++){
//				log.info((Object[]) list.get(i));
				Object[] sub = (Object[]) list.get(i);
				strBuffer.append(sub[1]+"-"+sub[0]+"[-"+sub[2]+"]:"+sub[3]+";");
			}
		}
		return strBuffer.toString();
	}

	@Override
	public Map<String, Object> getSqlMapByFlow(String ksbh, String ksrq) {
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		Object[] objects = {ksbh,ksrq+" 00:00:00",ksrq+" 23:59:59"};
		List<Object> para = new ArrayList<Object>();;
		para.add(ksbh);
		para.add(ksrq+" 00:00:00");
		para.add(ksrq+" 23:59:59");
		List<?> list = super.getListByHql("from W2Flowrec where ksbh = ? and kssj >= to_date(?,'yyyy-mm-dd hh24:mi:ss') and kssj <= to_date(?,'yyyy-mm-dd hh24:mi:ss')",objects);
		if(CollectionUtils.isNotEmpty(list)){
			hql.append("select obj.id,obj.kscs as 次数,obj.kssj as 考试时间,obj.ksxm as 考试项目,obj.kfdm as 扣分原因,obj.zp as 照片,obj.xmmc as 项目进度,obj.kfdm as 扣分原因  from W2Flowrec obj where 1=1 and obj.ksbh = ? and obj.kssj >= to_date(?,'yyyy-mm-dd hh24:mi:ss') and obj.kssj <= to_date(?,'yyyy-mm-dd hh24:mi:ss')");
		}else{
			hql.append("select obj.id,obj.kscs as 次数,obj.kssj as 考试时间,obj.ksxm as 考试项目,obj.kfdm as 扣分原因,obj.zp as 照片,obj.xmmc as 项目进度,obj.kfdm as 扣分原因  from W2Flowlog obj where 1=1 and obj.ksbh = ? and obj.kssj >= to_date(?,'yyyy-mm-dd hh24:mi:ss') and obj.kssj <= to_date(?,'yyyy-mm-dd hh24:mi:ss')");
		}
		hql.append(" order by kssj asc ");
		sqlMap.put(Const.PAGE_SQL, hql.toString());
		sqlMap.put(Const.SQL_PARA, para);
		return sqlMap;
	}

	@Override
	public Map<Integer, ArrayList<Integer>> getPrintImages(Integer id) {
		W2Records w2Records = (W2Records)super.loadModel(W2Records.class, id);
		if(w2Records != null){
			List<?> list = super.getListByHql("select id,kssj from W2Flowrec where 1=1 and ksbh = ? and ykrq = ? ", new Object[]{w2Records.getKsbh(),w2Records.getYkrq()});
			if(CollectionUtils.isEmpty(list)){
				list = super.getListByHql("select id,kssj from W2Flowlog where 1=1 and ksbh = ? and ykrq = ? ", new Object[]{w2Records.getKsbh(),w2Records.getYkrq()});
			}
			if(CollectionUtils.isNotEmpty(list)){
				int status = 2,fir=0;
				List<Integer> firList = new ArrayList<Integer>();
				List<Integer> secList = new ArrayList<Integer>();
				List<Integer> firIds = new ArrayList<Integer>();
				List<Integer> secIds = new ArrayList<Integer>();
				
				if(w2Records.getKsrq2() == null){
					status = 1;
				}
				for(int i=0;i<list.size();i++){
					Object[] sub = (Object[])list.get(i);
					log.info(sub[1]);
					if(status == 1 && w2Records.getKssj1().compareTo((Timestamp)sub[1]) == 0){
						fir = 1;
						firIds.add((int)sub[0]);
					}
					if(status == 1 && w2Records.getJssj1().compareTo((Timestamp)sub[1]) == 0){
						firIds.add((int)sub[0]);
					}
					if(fir == 1){
						firList.add((int)sub[0]);
					}else if(fir == 2){
						secList.add((int)sub[0]);
					}
					if(status == 2 && w2Records.getKssj2().compareTo((Timestamp)sub[1]) == 0){
						fir = 2;
						secIds.add((int)sub[0]);
					}
					if(status == 2 && w2Records.getJssj2().compareTo((Timestamp)sub[1]) == 0){
						secIds.add((int)sub[0]);
					}
				}
				Random rand = new Random();
				Map<Integer,ArrayList<Integer>> imageIds = new HashMap<Integer, ArrayList<Integer>>();
				if(CollectionUtils.isNotEmpty(firIds)){
					ArrayList<Integer> list1 = new ArrayList<Integer>();
					for(int i= 0;i<2;i++){
						if(i==1){
							firIds.add(firList.get(rand.nextInt(firList.size())));
						} 
						list1.add(firIds.get(i));
					}
					imageIds.put(1, list1);
				}
				if(CollectionUtils.isNotEmpty(secIds)){
					ArrayList<Integer> list2 = new ArrayList<Integer>();
					for(int i= 0;i<2;i++){
						if(i==1){
							secIds.add(secList.get(rand.nextInt(secList.size())));
						} 
						list2.add(secIds.get(i));
					}
					imageIds.put(2, list2);
				}
				return imageIds;
				
			}
		}
		return null;
	}
}
