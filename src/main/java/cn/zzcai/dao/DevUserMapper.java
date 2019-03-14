package cn.zzcai.dao;

import org.apache.ibatis.annotations.Param;

import cn.zzcai.pojo.DevUser;

/**
* @Description:    DevUser接口
* @Author:         zzcai
* @CreateDate:     2019/3/7 14:52
* @UpdateUser:     zzcai
* @UpdateDate:     2019/3/7 14:52
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public interface DevUserMapper {
	DevUser findByDevCode(@Param("devCode") String devCode);
}
