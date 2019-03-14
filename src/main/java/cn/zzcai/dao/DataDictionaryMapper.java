package cn.zzcai.dao;

import cn.zzcai.pojo.DataDictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataDictionaryMapper {
    List<DataDictionary> findDataDictionaryList(@Param("typeCode")String typeCode);
}
