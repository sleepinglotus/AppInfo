package cn.zzcai.service.impl;

import cn.zzcai.dao.DataDictionaryMapper;
import cn.zzcai.pojo.DataDictionary;
import cn.zzcai.service.DataDictionaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("dataDictionaryService")
public class DataDictionaryServiceImpl implements DataDictionaryService {

    @Resource
    private DataDictionaryMapper dataDictionaryMapper;

    @Override
    public List<DataDictionary> findDataDictionaryList(String typeCode) {
        return dataDictionaryMapper.findDataDictionaryList(typeCode);
    }
}
