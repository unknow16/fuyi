package com.fuyi.upms.rpc.mapper;

import com.fuyi.upms.rpc.entity.UpmsOrganization;
import com.fuyi.upms.rpc.entity.UpmsOrganizationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UpmsOrganizationMapper {
    int countByExample(UpmsOrganizationExample example);

    int deleteByExample(UpmsOrganizationExample example);

    int deleteByPrimaryKey(Integer organizationId);

    int insert(UpmsOrganization record);

    int insertSelective(UpmsOrganization record);

    List<UpmsOrganization> selectByExample(UpmsOrganizationExample example);

    UpmsOrganization selectByPrimaryKey(Integer organizationId);

    int updateByExampleSelective(@Param("record") UpmsOrganization record, @Param("example") UpmsOrganizationExample example);

    int updateByExample(@Param("record") UpmsOrganization record, @Param("example") UpmsOrganizationExample example);

    int updateByPrimaryKeySelective(UpmsOrganization record);

    int updateByPrimaryKey(UpmsOrganization record);
}