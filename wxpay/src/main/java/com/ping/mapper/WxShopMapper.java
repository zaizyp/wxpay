package com.ping.mapper;

import com.ping.pojo.WxShop;
import com.ping.pojo.WxShopExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxShopMapper {
    int countByExample(WxShopExample example);

    int deleteByExample(WxShopExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WxShop record);

    int insertSelective(WxShop record);

    List<WxShop> selectByExample(WxShopExample example);

    WxShop selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WxShop record, @Param("example") WxShopExample example);

    int updateByExample(@Param("record") WxShop record, @Param("example") WxShopExample example);

    int updateByPrimaryKeySelective(WxShop record);

    int updateByPrimaryKey(WxShop record);
}