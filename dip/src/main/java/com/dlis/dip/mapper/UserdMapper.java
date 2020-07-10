package com.dlis.dip.mapper;

import com.dlis.dip.mode.Userd;
import com.dlis.dip.mode.UserdExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface UserdMapper {
    long countByExample(UserdExample example);

    int deleteByExample(UserdExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Userd record);

    int insertSelective(Userd record);

    List<Userd> selectByExample(UserdExample example);

    Userd selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Userd record, @Param("example") UserdExample example);

    int updateByExample(@Param("record") Userd record, @Param("example") UserdExample example);

    int updateByPrimaryKeySelective(Userd record);

    int updateByPrimaryKey(Userd record);
}