package com.ping.mapper;

import com.ping.pojo.WxOrderNo;
import com.ping.pojo.WxOrderNoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxOrderNoMapper {
    int countByExample(WxOrderNoExample example);

    int deleteByExample(WxOrderNoExample example);

    int deleteByPrimaryKey(String outTradeNo);

    int insert(WxOrderNo record);

    int insertSelective(WxOrderNo record);

    List<WxOrderNo> selectByExample(WxOrderNoExample example);

    WxOrderNo selectByPrimaryKey(String outTradeNo);

    int updateByExampleSelective(@Param("record") WxOrderNo record, @Param("example") WxOrderNoExample example);

    int updateByExample(@Param("record") WxOrderNo record, @Param("example") WxOrderNoExample example);

    int updateByPrimaryKeySelective(WxOrderNo record);

    int updateByPrimaryKey(WxOrderNo record);
}