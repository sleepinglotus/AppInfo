package cn.zzcai.service.impl;


import java.util.List;

import javax.annotation.Resource;

import cn.zzcai.dao.AppInfoMapper;
import cn.zzcai.pojo.AppInfo;
import cn.zzcai.service.AppInfoService;
import cn.zzcai.tools.PageBean;
import org.springframework.stereotype.Service;

@Service("appInfoService")
public class AppInfoServiceImpl implements AppInfoService {
	
	@Resource
	private AppInfoMapper appInfoMapper;
	
	@Override
	public PageBean<AppInfo> findByPage(String softwareName, Integer status, Integer flatformId, Integer categoryLevel1,
			Integer categoryLevel2, Integer categoryLevel3, Integer devId, Integer pageNo, Integer pageSize) {
		AppInfo appInfo=new AppInfo();
		appInfo.setSoftwareName(softwareName);
		appInfo.setStatus(status);
		appInfo.setFlatformId(flatformId);
		appInfo.setCategoryLevel1(categoryLevel1);
		appInfo.setCategoryLevel2(categoryLevel2);
		appInfo.setCategoryLevel3(categoryLevel3);
		appInfo.setDevId(devId);
		PageBean<AppInfo> pageBean=new PageBean<>();
		pageBean.setPageSize(pageSize);
		int totalCount=appInfoMapper.getTotalCount(softwareName, status, flatformId, categoryLevel1,
				categoryLevel2, categoryLevel3, devId);
		pageBean.setTotalCount(totalCount);
		pageBean.setPageNo(pageNo);
		Integer from=(pageNo-1)*pageSize;
		List<AppInfo> pageList=appInfoMapper.findByPage(softwareName, status, flatformId, categoryLevel1,
				categoryLevel2, categoryLevel3, devId, from, pageSize);
		pageBean.setPageList(pageList);
		return pageBean;
	}

	@Override
	public AppInfo checkAPKName(String APKName) {
		return appInfoMapper.findAppinfo(null,APKName);
	}

	@Override
	public AppInfo findAppinfo(Integer id) {
		return appInfoMapper.findAppinfo(id,null);
	}

	@Override
	public int addAppInfo(AppInfo appInfo) {
		return appInfoMapper.addAppInfo(appInfo);
	}

}
