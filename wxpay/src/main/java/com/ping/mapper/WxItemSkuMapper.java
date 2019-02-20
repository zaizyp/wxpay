package com.ping.mapper;

import com.ping.pojo.WxItemSku;
import com.ping.pojo.WxItemSkuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxItemSkuMapper {
    int countByExample(WxItemSkuExample example);

    int deleteByExample(WxItemSkuExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WxItemSku record);

    int insertSelective(WxItemSku record);

    List<WxItemSku> selectByExample(WxItemSkuExample example);

    WxItemSku selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WxItemSku record, @Param("example") WxItemSkuExample example);

    int updateByExample(@Param("record") WxItemSku record, @Param("example") WxItemSkuExample example);

    int updateByPrimaryKeySelective(WxItemSku record);

    int updateByPrimaryKey(WxItemSku record);
}