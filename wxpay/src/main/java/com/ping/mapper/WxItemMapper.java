package com.ping.mapper;

import com.ping.pojo.WxItem;
import com.ping.pojo.WxItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxItemMapper {
    int countByExample(WxItemExample example);

    int deleteByExample(WxItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WxItem record);

    int insertSelective(WxItem record);

    List<WxItem> selectByExample(WxItemExample example);

    WxItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WxItem record, @Param("example") WxItemExample example);

    int updateByExample(@Param("record") WxItem record, @Param("example") WxItemExample example);

    int updateByPrimaryKeySelective(WxItem record);

    int updateByPrimaryKey(WxItem record);
}