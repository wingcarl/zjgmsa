package com.thinkgem.jeesite.common.utils.excel.fieldtype;

import java.util.List;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.utils.Collections3;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

public class UserListType {
	private static SystemService systemService = SpringContextHolder.getBean(SystemService.class);
	
		/**
		 * 获取对象值（导入）
		 */
		public static Object getValue(String val) {
			List<User> userList = Lists.newArrayList();
			List<User> allUserList = UserUtils.getUserList();
			for (String s : StringUtils.split(val, ",")){
				for (User e : allUserList){
					if (StringUtils.trimToEmpty(s.replace(" ", "")).equals(StringUtils.trimToEmpty(e.getName().replace(" ", "")))){
						userList.add(e);
					}
				}
			}
			return userList.size()>0?userList:null;
		}
	
		/**
		 * 设置对象值（导出）
		 */
		public static String setValue(Object val) {
			if (val != null){
				@SuppressWarnings("unchecked")
				List<User> userList = (List<User>)val;
				return Collections3.extractToString(userList, "name", ", ");
			}
			return "";
		}
	
}
