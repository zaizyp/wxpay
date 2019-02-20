package com.ping.mapper;

import com.ping.pojo.WxNews;
import com.ping.pojo.WxNewsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxNewsMapper {
    int countByExample(WxNewsExample example);

    int deleteByExample(WxNewsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WxNews record);

    int insertSelective(WxNews record);

    List<WxNews> selectByExample(WxNewsExample example);

    WxNews selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WxNews record, @Param("example") WxNewsExample example);

    int updateByExample(@Param("record") WxNews record, @Param("example") WxNewsExample example);

    int updateByPrimaryKeySelective(WxNews record);

    int updateByPrimaryKey(WxNews record);
}