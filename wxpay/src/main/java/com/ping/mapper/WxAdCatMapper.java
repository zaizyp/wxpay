package com.ping.mapper;

import com.ping.pojo.WxAdCat;
import com.ping.pojo.WxAdCatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxAdCatMapper {
    int countByExample(WxAdCatExample example);

    int deleteByExample(WxAdCatExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WxAdCat record);

    int insertSelective(WxAdCat record);

    List<WxAdCat> selectByExample(WxAdCatExample example);

    WxAdCat selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WxAdCat record, @Param("example") WxAdCatExample example);

    int updateByExample(@Param("record") WxAdCat record, @Param("example") WxAdCatExample example);

    int updateByPrimaryKeySelective(WxAdCat record);

    int updateByPrimaryKey(WxAdCat record);
}