package com.ping.mapper;

import com.ping.pojo.WxItemSpec;
import com.ping.pojo.WxItemSpecExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxItemSpecMapper {
    int countByExample(WxItemSpecExample example);

    int deleteByExample(WxItemSpecExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WxItemSpec record);

    int insertSelective(WxItemSpec record);

    List<WxItemSpec> selectByExample(WxItemSpecExample example);

    WxItemSpec selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WxItemSpec record, @Param("example") WxItemSpecExample example);

    int updateByExample(@Param("record") WxItemSpec record, @Param("example") WxItemSpecExample example);

    int updateByPrimaryKeySelective(WxItemSpec record);

    int updateByPrimaryKey(WxItemSpec record);
}