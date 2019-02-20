package com.ping.mapper;

import com.ping.pojo.WxItemCat;
import com.ping.pojo.WxItemCatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxItemCatMapper {
    int countByExample(WxItemCatExample example);

    int deleteByExample(WxItemCatExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WxItemCat record);

    int insertSelective(WxItemCat record);

    List<WxItemCat> selectByExample(WxItemCatExample example);

    WxItemCat selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WxItemCat record, @Param("example") WxItemCatExample example);

    int updateByExample(@Param("record") WxItemCat record, @Param("example") WxItemCatExample example);

    int updateByPrimaryKeySelective(WxItemCat record);

    int updateByPrimaryKey(WxItemCat record);
}