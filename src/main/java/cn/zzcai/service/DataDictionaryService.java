package cn.zzcai.service;

import cn.zzcai.pojo.DataDictionary;

import java.util.List;

public interface DataDictionaryService {
    List<DataDictionary> findDataDictionaryList(String typeCode);
}
