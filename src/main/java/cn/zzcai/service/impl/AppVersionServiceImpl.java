package cn.zzcai.service.impl;

import cn.zzcai.dao.AppInfoMapper;
import cn.zzcai.dao.AppVersionMapper;
import cn.zzcai.pojo.AppInfo;
import cn.zzcai.pojo.AppVersion;
import cn.zzcai.service.AppVersionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("appVersionService")
public class AppVersionServiceImpl implements AppVersionService {
    @Resource
    private AppVersionMapper appVersionMapper;
    @Resource
    private AppInfoMapper appInfoMapper;

    @Override
    public List<AppVersion> findAppVersionList(Integer appId) {
        return appVersionMapper.findAppVersionList(appId);
    }

    @Override
    public AppVersion findAppVersionById(Integer id) {
        return appVersionMapper.findAppVersionById(id);
    }

    @Override
    public boolean addAppVersion(AppVersion appVersion) {
        int ret=appVersionMapper.addAppVersion(appVersion);
        System.out.println(appVersion.getAppId());
        System.out.println(appVersion.getId());
        if(ret>0){
            AppInfo appInfo=new AppInfo();
            appInfo.setId(appVersion.getAppId());
            appInfo.setVersionId(appVersion.getId());
            int ret2=appInfoMapper.updateAppInfo(appInfo);
            if(ret2>0){
                return true;
            }
            else {
                return false;
            }
        }else {
            return false;
        }
    }
}
