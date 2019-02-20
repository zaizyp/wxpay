package com.ping.mapper;

import com.ping.pojo.WxNewsContent;
import com.ping.pojo.WxNewsContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxNewsContentMapper {
    int countByExample(WxNewsContentExample example);

    int deleteByExample(WxNewsContentExample example);

    int deleteByPrimaryKey(Long newsId);

    int insert(WxNewsContent record);

    int insertSelective(WxNewsContent record);

    List<WxNewsContent> selectByExampleWithBLOBs(WxNewsContentExample example);

    List<WxNewsContent> selectByExample(WxNewsContentExample example);

    WxNewsContent selectByPrimaryKey(Long newsId);

    int updateByExampleSelective(@Param("record") WxNewsContent record, @Param("example") WxNewsContentExample example);

    int updateByExampleWithBLOBs(@Param("record") WxNewsContent record, @Param("example") WxNewsContentExample example);

    int updateByExample(@Param("record") WxNewsContent record, @Param("example") WxNewsContentExample example);

    int updateByPrimaryKeySelective(WxNewsContent record);

    int updateByPrimaryKeyWithBLOBs(WxNewsContent record);
}