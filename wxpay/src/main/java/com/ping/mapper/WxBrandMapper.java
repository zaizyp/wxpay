package com.ping.mapper;

import com.ping.pojo.WxBrand;
import com.ping.pojo.WxBrandExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxBrandMapper {
    int countByExample(WxBrandExample example);

    int deleteByExample(WxBrandExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WxBrand record);

    int insertSelective(WxBrand record);

    List<WxBrand> selectByExample(WxBrandExample example);

    WxBrand selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WxBrand record, @Param("example") WxBrandExample example);

    int updateByExample(@Param("record") WxBrand record, @Param("example") WxBrandExample example);

    int updateByPrimaryKeySelective(WxBrand record);

    int updateByPrimaryKey(WxBrand record);
}