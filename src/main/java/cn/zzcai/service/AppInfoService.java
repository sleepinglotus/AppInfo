package cn.zzcai.service;

import cn.zzcai.pojo.AppInfo;
import cn.zzcai.tools.PageBean;

public interface AppInfoService {
	
	PageBean<AppInfo> findByPage(String softwareName, Integer status, Integer flatformId, Integer categoryLevel1,
                                 Integer categoryLevel2, Integer categoryLevel3, Integer devId, Integer pageNo, Integer pageSize);

	AppInfo checkAPKName(String APKName);

	AppInfo findAppinfo(Integer id);

	int addAppInfo(AppInfo appInfo);
}
