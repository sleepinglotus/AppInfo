package cn.zzcai.dao;

import cn.zzcai.pojo.AppCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppCategoryMapper {
    List<AppCategory> findAppCategoryList(@Param("parentId")Integer parentId);
}
