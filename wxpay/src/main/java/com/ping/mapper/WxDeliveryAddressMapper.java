package com.ping.mapper;

import com.ping.pojo.WxDeliveryAddress;
import com.ping.pojo.WxDeliveryAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxDeliveryAddressMapper {
    int countByExample(WxDeliveryAddressExample example);

    int deleteByExample(WxDeliveryAddressExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WxDeliveryAddress record);

    int insertSelective(WxDeliveryAddress record);

    List<WxDeliveryAddress> selectByExample(WxDeliveryAddressExample example);

    WxDeliveryAddress selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WxDeliveryAddress record, @Param("example") WxDeliveryAddressExample example);

    int updateByExample(@Param("record") WxDeliveryAddress record, @Param("example") WxDeliveryAddressExample example);

    int updateByPrimaryKeySelective(WxDeliveryAddress record);

    int updateByPrimaryKey(WxDeliveryAddress record);
}