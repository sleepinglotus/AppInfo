package cn.zzcai.service.impl;

import cn.zzcai.dao.AppVersionMapper;
import cn.zzcai.pojo.AppVersion;
import cn.zzcai.service.AppVersionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("appVersionService")
public class AppVersionServiceImpl implements AppVersionService {
    @Resource
    private AppVersionMapper appVersionMapper;

    @Override
    public List<AppVersion> findAppVersionList(Integer appId) {
        return appVersionMapper.findAppVersionList(appId);
    }

    @Override
    public AppVersion findAppVersionById(Integer id) {
        return appVersionMapper.findAppVersionById(id);
    }

    @Override
    public int addAppVersion(AppVersion appVersion) {
        return appVersionMapper.addAppVersion(appVersion);
    }
}
