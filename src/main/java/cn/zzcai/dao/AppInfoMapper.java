package cn.zzcai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zzcai.pojo.AppInfo;

/**
* @Description:    AppInfo接口
* @Author:         zzcai
* @CreateDate:     2019/3/7 14:56
* @UpdateUser:     zzcai
* @UpdateDate:     2019/3/7 14:56
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public interface AppInfoMapper {
	
	List<AppInfo> findByPage(@Param("softwareName")String softwareName,
							 @Param("status")Integer status,
							 @Param("flatformId")Integer flatformId,
							 @Param("categoryLevel1")Integer categoryLevel1,
							 @Param("categoryLevel2")Integer categoryLevel2,
							 @Param("categoryLevel3")Integer categoryLevel3,
							 @Param("devId")Integer devId,
							 @Param("from") Integer from,
                             @Param("pageSize") Integer pageSize);
	
	int getTotalCount(@Param("softwareName")String softwareName,
					  @Param("status")Integer status,
					  @Param("flatformId")Integer flatformId,
					  @Param("categoryLevel1")Integer categoryLevel1,
					  @Param("categoryLevel2")Integer categoryLevel2,
					  @Param("categoryLevel3")Integer categoryLevel3,
					  @Param("devId")Integer devId);

	AppInfo findAppinfo(@Param("id")Integer id,@Param("APKName")String APKName);

	int addAppInfo(AppInfo appInfo);
}
