package com.ping.mapper;

import com.ping.pojo.WxItemDesc;
import com.ping.pojo.WxItemDescExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxItemDescMapper {
    int countByExample(WxItemDescExample example);

    int deleteByExample(WxItemDescExample example);

    int deleteByPrimaryKey(Long itemId);

    int insert(WxItemDesc record);

    int insertSelective(WxItemDesc record);

    List<WxItemDesc> selectByExampleWithBLOBs(WxItemDescExample example);

    List<WxItemDesc> selectByExample(WxItemDescExample example);

    WxItemDesc selectByPrimaryKey(Long itemId);

    int updateByExampleSelective(@Param("record") WxItemDesc record, @Param("example") WxItemDescExample example);

    int updateByExampleWithBLOBs(@Param("record") WxItemDesc record, @Param("example") WxItemDescExample example);

    int updateByExample(@Param("record") WxItemDesc record, @Param("example") WxItemDescExample example);

    int updateByPrimaryKeySelective(WxItemDesc record);

    int updateByPrimaryKeyWithBLOBs(WxItemDesc record);

    int updateByPrimaryKey(WxItemDesc record);
}