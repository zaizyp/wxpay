package com.ping.mapper;

import com.ping.pojo.WxShopingCart;
import com.ping.pojo.WxShopingCartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxShopingCartMapper {
    int countByExample(WxShopingCartExample example);

    int deleteByExample(WxShopingCartExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WxShopingCart record);

    int insertSelective(WxShopingCart record);

    List<WxShopingCart> selectByExample(WxShopingCartExample example);

    WxShopingCart selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WxShopingCart record, @Param("example") WxShopingCartExample example);

    int updateByExample(@Param("record") WxShopingCart record, @Param("example") WxShopingCartExample example);

    int updateByPrimaryKeySelective(WxShopingCart record);

    int updateByPrimaryKey(WxShopingCart record);
}