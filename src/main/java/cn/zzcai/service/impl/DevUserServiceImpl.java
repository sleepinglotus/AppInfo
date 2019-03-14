package cn.zzcai.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zzcai.dao.DevUserMapper;
import cn.zzcai.pojo.DevUser;
import cn.zzcai.service.DevUserService;

/**
 * @author zzcai
 * @version 1.0
 * @date 2019年3月5日 下午3:55:03
 * @remark TODO
 */
@Service("devUserService")
public class DevUserServiceImpl implements DevUserService {
	
	@Resource
	private DevUserMapper devUserMapper;

	@Override
	public DevUser doLogin(String devCode, String devPassword) {
		DevUser devUser=devUserMapper.findByDevCode(devCode);
		if(devUser!=null&&devUser.getDevPassword().equals(devPassword)) {
			return devUser;
		}
		return null;
	}
	
}
