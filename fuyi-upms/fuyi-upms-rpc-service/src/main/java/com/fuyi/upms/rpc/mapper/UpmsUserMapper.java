package com.fuyi.upms.rpc.mapper;

import com.fuyi.upms.rpc.entity.UpmsUser;
import com.fuyi.upms.rpc.entity.UpmsUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UpmsUserMapper {
    int countByExample(UpmsUserExample example);

    int deleteByExample(UpmsUserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(UpmsUser record);

    int insertSelective(UpmsUser record);

    List<UpmsUser> selectByExample(UpmsUserExample example);

    UpmsUser selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") UpmsUser record, @Param("example") UpmsUserExample example);

    int updateByExample(@Param("record") UpmsUser record, @Param("example") UpmsUserExample example);

    int updateByPrimaryKeySelective(UpmsUser record);

    int updateByPrimaryKey(UpmsUser record);
}