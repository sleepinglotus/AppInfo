package cn.zzcai.dao;

import cn.zzcai.pojo.AppVersion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppVersionMapper {

    List<AppVersion> findAppVersionList(@Param("appId")Integer id);

    AppVersion findAppVersionById(@Param("id")Integer id);

    int addAppVersion(AppVersion appVersion);
}
