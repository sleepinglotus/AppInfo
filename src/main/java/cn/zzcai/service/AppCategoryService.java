package cn.zzcai.service;

import cn.zzcai.pojo.AppCategory;

import java.util.List;

public interface AppCategoryService {
    List<AppCategory> findAppCategoryList(Integer parentId);
}
