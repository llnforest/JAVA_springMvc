package com.service.system.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.common.response.ResponseModel;
import com.common.spring.BeanHelper;
import com.common.utils.Const;
import com.common.utils.DateUtil;
import com.common.utils.SearchUtil;
import com.common.utils.TableUtil;
import com.model.system.SysBlank;
import com.model.system.SysConfig;
import com.model.system.SysLog;
import com.model.system.SysUser;
import com.service.impl.BaseManager;
import com.service.system.BlankService;
import com.service.system.ConfigService;
import com.sun.istack.internal.logging.Logger;

/**  
 *  
 * @author:Lynn
 * @version:V1.0
 * 2018年12月24日  
 */
@Service
public class BlankManager extends BaseManager implements BlankService{
	@Override
	public Map<String, Object> getSqlMap(ModelMap map){
		//查询工具类
		StringBuffer hql = new StringBuffer();
		log.info(TableUtil.getSelect(SysBlank.class.getName()));
		hql.append("select "+TableUtil.getSelect(SysBlank.class.getName()));
		hql.append("from SysBlank obj left join obj.sysUser sysUser where 1=1  ");
		
		SearchUtil searchUtil = new SearchUtil(hql);
		searchUtil.setHql(hql);
		searchUtil.where(map);
		searchUtil.setOrder("obj.blankType");
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put(Const.PAGE_SQL, searchUtil.hql.toString());
		sqlMap.put(Const.SQL_PARA, searchUtil.para);
		return sqlMap;
	}

	@Override
	public ResponseModel batchUnlock(ModelMap map) {
		ResponseModel rmModel = new ResponseModel(); 
		String ids = String.valueOf(map.get("ids"));
		if(ids.length() == 0){
			rmModel.setCodeAndDesc("1", "请勾选要解锁的黑名单");
		}else{
			String[] idsStrings = ids.split(",");
			ids = "\'"+StringUtils.join(idsStrings,"\',\'")+"\'";
		}
		try {
			baseDao.executeHql("update SysBlank set dataFlag = 'Y',modifyId = ?,modifyTime = ? where blankId in ("+ids+") and dataFlag = 'N' ", new Object[]{map.get("modifyId"),new Date()});
			rmModel.setDesc("批量解锁成功");
		} catch (Exception e) {
			rmModel.setCodeAndDesc("2", "批量解锁失败");
		}
		return rmModel;
		
	}

	@Override
	public void addBlank(String blankType, String name) {
		String logHql,limitTimes,blankHql;
		SysBlank sysBlank;
		ConfigService configService = BeanHelper.getBean("configManager");
		if(blankType.equals("1")){
			//用户黑名单判断 1
			SysUser sysUser = (SysUser) loadModel(SysUser.class, name);
			if(sysUser == null) return;
			logHql = " obj.createId = ? and obj.operateType = ? ";
			sysBlank = new SysBlank(sysUser,blankType,null,"N");
			limitTimes = configService.getValueByCode("userLoginTimes");
			blankHql = " obj.sysUser.userId = ? ";
		}else if(blankType.equals("2")){
			//终端黑名单判断 2
			logHql = " obj.clientMac = ? and obj.operateType = ? ";
			sysBlank = new SysBlank(null,blankType,name,"N");
			limitTimes = configService.getValueByCode("clientLoginTimes");
			blankHql = " obj.clientMac = ? ";
		}else{
			return;
		}
		//根据黑名单已有信息判断是否允许再次加入
		SysBlank blank = (SysBlank)getOneByHql("from SysBlank obj where obj.dataFlag = ? and obj.blankType = ? and " + blankHql + " order by obj.createTime desc", new Object[]{"N",blankType,name});
		if(blank != null){
			log.info(blank.getCreateTime());
			int limitTime = 0;
			String limitTimeString = configService.getValueByCode("blankLimitTime");
			if(StringUtils.isNotEmpty(limitTimeString)){
				limitTime = Integer.parseInt(limitTimeString);
			}
			Date lastDate = DateUtil.addSeconds(blank.getCreateTime(), limitTime*3600);
			Date nowDate = new Date();
			int compareNum = nowDate.compareTo(lastDate);
			if(compareNum != 1) return;
		}
		//根据登录日志次数判断是否加入黑名单
		List<?> logList = getListWithLimitByHql("from SysLog obj where "+logHql+" order by obj.createTime desc", "", limitTimes, new Object[]{name,Const.LOG_LOGIN_TYPE});
		log.info(logList.size());
		for(int i = 0; i < logList.size(); i ++){
			SysLog sysLog = (SysLog)logList.get(i);
			if(sysLog.getOperateResult().equals(Const.LOG_SUCCESS)){
				return;
			}
		}
		try {
			saveModel(sysBlank);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public Boolean isCanLogin(String blankType, String name) {
		String blankHql;
		int limitTime = 0;
		if(blankType.equals("1")){
			blankHql = " obj.sysUser.userId = ? and obj.blankType = ? and obj.dataFlag = ? ";
		}else if(blankType.equals("2")){
			//终端黑名单判断 2
			blankHql = " obj.clientMac = ? and obj.blankType = ? and obj.dataFlag = ? ";
		}else{
			return false;
		}
		SysBlank sysBlank = (SysBlank)getOneByHql("from SysBlank obj where "+blankHql+" order by obj.createTime desc", new Object[]{name,blankType,"N"});
		if(sysBlank == null) return true;
		ConfigService configService = BeanHelper.getBean("configManager");
		String limitTimeString = configService.getValueByCode("blankLimitTime");
		if(StringUtils.isNotEmpty(limitTimeString)){
			limitTime = Integer.parseInt(limitTimeString);
		}
		Date lastDate = DateUtil.addSeconds(sysBlank.getCreateTime(), limitTime*3600);
		Date nowDate = new Date();
		int compareNum = nowDate.compareTo(lastDate);
		if(compareNum == 1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void autoUnLock() {
		int limitTime = 0;
		ConfigService configService = BeanHelper.getBean("configManager");
		String limitTimeString = configService.getValueByCode("blankLimitTime");
		if(StringUtils.isNotEmpty(limitTimeString)){
			limitTime = Integer.parseInt(limitTimeString);
		}else{
			return;
		}
		Date beforeDate = DateUtil.addSeconds(new Date(), -(limitTime*3600));
		executeHql("update SysBlank set dataFlag = ? where createTime <= ? ", new Object[]{"Y",beforeDate});
		
	}
	
}
