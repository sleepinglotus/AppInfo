package cn.zzcai.service;

import cn.zzcai.pojo.BackendUser;

/**
 * @author zzcai
 * @version 1.0
 * @date 2019��3��5�� ����11:45:02
 * @remark TODO
 */
public interface BackendUserService {
	
	BackendUser doLogin(String userCode, String userPassword);
}
