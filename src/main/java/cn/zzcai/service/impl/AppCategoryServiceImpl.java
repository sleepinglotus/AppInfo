package cn.zzcai.service.impl;

import cn.zzcai.dao.AppCategoryMapper;
import cn.zzcai.pojo.AppCategory;
import cn.zzcai.service.AppCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("appCategoryService")
public class AppCategoryServiceImpl implements AppCategoryService {
    @Resource
    private AppCategoryMapper appCategoryMapper;
    @Override
    public List<AppCategory> findAppCategoryList(Integer parentId) {
        return appCategoryMapper.findAppCategoryList(parentId);
    }
}
