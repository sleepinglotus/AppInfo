package cn.zzcai.service;

import cn.zzcai.pojo.AppVersion;

import java.util.List;

public interface AppVersionService {
    List<AppVersion> findAppVersionList(Integer appId);

    AppVersion findAppVersionById(Integer id);

    int addAppVersion(AppVersion appVersion);
}
