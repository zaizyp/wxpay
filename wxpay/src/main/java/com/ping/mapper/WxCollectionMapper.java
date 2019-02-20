package com.ping.mapper;

import com.ping.pojo.WxCollection;
import com.ping.pojo.WxCollectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxCollectionMapper {
    int countByExample(WxCollectionExample example);

    int deleteByExample(WxCollectionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WxCollection record);

    int insertSelective(WxCollection record);

    List<WxCollection> selectByExample(WxCollectionExample example);

    WxCollection selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WxCollection record, @Param("example") WxCollectionExample example);

    int updateByExample(@Param("record") WxCollection record, @Param("example") WxCollectionExample example);

    int updateByPrimaryKeySelective(WxCollection record);

    int updateByPrimaryKey(WxCollection record);
}