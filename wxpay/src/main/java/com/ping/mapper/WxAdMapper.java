package com.ping.mapper;

import com.ping.pojo.WxAd;
import com.ping.pojo.WxAdExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxAdMapper {
    int countByExample(WxAdExample example);

    int deleteByExample(WxAdExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WxAd record);

    int insertSelective(WxAd record);

    List<WxAd> selectByExampleWithBLOBs(WxAdExample example);

    List<WxAd> selectByExample(WxAdExample example);

    WxAd selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WxAd record, @Param("example") WxAdExample example);

    int updateByExampleWithBLOBs(@Param("record") WxAd record, @Param("example") WxAdExample example);

    int updateByExample(@Param("record") WxAd record, @Param("example") WxAdExample example);

    int updateByPrimaryKeySelective(WxAd record);

    int updateByPrimaryKeyWithBLOBs(WxAd record);

    int updateByPrimaryKey(WxAd record);
}