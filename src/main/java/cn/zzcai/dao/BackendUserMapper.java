package cn.zzcai.dao;

import org.apache.ibatis.annotations.Param;

import cn.zzcai.pojo.BackendUser;

/**
* @Description:    BackendUser接口
* @Author:         zzcai
* @CreateDate:     2019/3/7 14:54
* @UpdateUser:     zzcai
* @UpdateDate:     2019/3/7 14:54
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public interface BackendUserMapper {
	BackendUser findByUserCode(@Param("userCode") String userCode);
}
