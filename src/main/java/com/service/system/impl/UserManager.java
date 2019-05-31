package com.service.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.common.response.ResponseModel;
import com.common.utils.Const;
import com.common.utils.DateUtil;
import com.common.utils.MapUtil;
import com.common.utils.Search;
import com.model.system.SysRole;
import com.model.system.SysUser;
import com.model.system.SysUserConfig;
import com.model.system.SysUserRole;
import com.service.impl.BaseManager;
import com.service.system.UserService;

/**  
 *  用户管理服务层
 * @author:wangzhen
 * @version:V1.0
 * 2017年4月26日  
 */
@Service
public class UserManager extends BaseManager implements UserService{
	
	@SuppressWarnings({"unchecked" })
	@Override
	public Map<String, Object> getSqlMap(ModelMap map) {
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
//		
		hql.append(" select obj.userId,obj.userNo as 用户编号,obj.userName as 用户姓名,obj.userCardId as 证件号码,obj.userType as 用户类型,obj.ip as 绑定IP,obj.userExpDate as 账号有效期,obj.pwdExpDate as 密码有效期,obj.limitStart as 允许登录时段,obj.limitEnd as 访问限制说明,obj.state as 访问限制说明,obj.dataFlag as 用户状态");
		hql.append(" from SysUser obj, SysUserRole userRole  where 1=1 and obj.userId = userRole.sysUser.userId ");
		if(MapUtil.isNotBlankKey(map,"userExpType")){
			String nowTime = DateUtil.getNowDateStr();
			if(map.get("userExpType").equals("0")){
				map.put("obj.userExpDate_>_date", nowTime);
			}else{
				map.put("obj.userExpDate_<=_date", nowTime);
			}
		}
		if(MapUtil.isNotBlankKey(map, "pwdExpType")){
			String nowTime = DateUtil.getNowDateStr();
			if(map.get("pwdExpType").equals("0")){
				map.put("obj.pwdExpDate_>_date", nowTime);
			}else{
				map.put("obj.pwdExpDate_<=_date", nowTime);
			}
		}
		Map<String, Object> where = (Map<String, Object>) Search.where(hql, map);
		hql = (StringBuffer) where.get("hql");
//		hql = Search.getGroup(hql, "obj.userId,obj.userNo,obj.userName,obj.userCardId,obj.userType,obj.ip,obj.userExpDate,obj.pwdExpDate,obj.limitStart,obj.limitEnd,obj.state,obj.dataFlag");
//		hql = Search.getOrder(hql,"obj.userOrder");
		sqlMap.put(Const.PAGE_SQL, hql.toString());
		sqlMap.put(Const.SQL_PARA, where.get("para"));
		return sqlMap;
	}

	@Override
	public boolean saveUserConfig(String userId, String type, String val,boolean isUnique) {
		SysUser sysUser = (SysUser) loadModel(SysUser.class, userId);
		if(isUnique){
			Map<String, String> map = new HashMap<String, String>();
			map.put("userId", userId);
			map.put("configType", type);
			SysUserConfig userConfig = (SysUserConfig)this.getUniqueByMap(SysUserConfig.class, map);
			if(userConfig!=null){
				userConfig.setConfigValue(val);
			}else{
				userConfig = new SysUserConfig(sysUser,type,val);
			}
			this.saveOrUpdateModel(userConfig);
		}else {
			SysUserConfig userConfig = new SysUserConfig(sysUser,type,val);
			try {
				this.saveModel(userConfig);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}

	@Override
	public boolean beforeDeleteModel(String userId) throws Exception {
		this.executeHql("delete from SysUserRole obj where obj.sysUser.userId = ?", userId);
		return true;
	}

	@SuppressWarnings("all")
	@Override
	public ResponseModel getRolesData(String userId) {
		ResponseModel rModel = new ResponseModel();
		List<SysRole> roleList = (List<SysRole>)this.getListByHql("from SysRole order by roleOrder asc");
		List data = new ArrayList();
		if(CollectionUtils.isNotEmpty(roleList)){
			for (SysRole role : roleList) {
				Map<String,Object> roleMap = new HashMap<String, Object>();
				roleMap.put("name", role.getRoleName());
				roleMap.put("value", role.getRoleId());
				if(StringUtils.isNotEmpty(userId)){
					roleMap.put("selected", this.isUserRole(userId, role.getRoleId()));
				}
				data.add(roleMap);
			}
		}
		rModel.setData(data);
		return rModel;
	}
	
	/**
	 * 判断是否授权某角色
	 * 2019年1月21日
	 * @param userId
	 * @param roleId
	 * @return
	 * @author:Lynn
	 */
	private boolean isUserRole(String userId,String roleId){
		Map<String,String> map = new HashMap<String, String>();
		map.put("sysUser.userId", userId);
		map.put("sysRole.roleId", roleId);
		SysUserRole userRole = (SysUserRole)getUniqueByMap(SysUserRole.class, map);
		if(userRole != null){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public String getRoleNames(String userId) {
		List<?> list = this.getListByHql("from SysUserRole obj where obj.sysUser.userId = ?", userId);
		List<String> nameList = new ArrayList<String>();
		if(CollectionUtils.isNotEmpty(list)){
			for(int i = 0; i < list.size();i++){
				SysUserRole userRole = (SysUserRole)list.get(i);
				SysRole role = (SysRole)this.getUniqueByHql("from SysRole obj where obj.roleId = ?", userRole.getSysRole().getRoleId());
				if(role != null) nameList.add(role.getRoleName());
			}
		}
		return StringUtils.join(nameList,",");
	}
	

}
