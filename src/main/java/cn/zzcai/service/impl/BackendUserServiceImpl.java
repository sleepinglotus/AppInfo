package cn.zzcai.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zzcai.dao.BackendUserMapper;
import cn.zzcai.pojo.BackendUser;
import cn.zzcai.service.BackendUserService;

/**
 * @author zzcai
 * @version 1.0
 * @date 2019年3月5日 上午11:44:57
 * @remark TODO
 */
@Service("backendUserService")
public class BackendUserServiceImpl implements BackendUserService {
	@Resource
	private BackendUserMapper backendUserMapper;

	@Override
	public BackendUser doLogin(String userCode, String userPassword) {
		BackendUser backendUser=backendUserMapper.findByUserCode(userCode);
		if(backendUser!=null&&backendUser.getUserPassword().equals(userPassword)) {
			return backendUser;
		}
		return null;
	}
	
	
}
