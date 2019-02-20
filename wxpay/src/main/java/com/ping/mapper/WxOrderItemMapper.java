package com.ping.mapper;

import com.ping.pojo.WxOrderItem;
import com.ping.pojo.WxOrderItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxOrderItemMapper {
    int countByExample(WxOrderItemExample example);

    int deleteByExample(WxOrderItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WxOrderItem record);

    int insertSelective(WxOrderItem record);

    List<WxOrderItem> selectByExample(WxOrderItemExample example);

    WxOrderItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WxOrderItem record, @Param("example") WxOrderItemExample example);

    int updateByExample(@Param("record") WxOrderItem record, @Param("example") WxOrderItemExample example);

    int updateByPrimaryKeySelective(WxOrderItem record);

    int updateByPrimaryKey(WxOrderItem record);
}