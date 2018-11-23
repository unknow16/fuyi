package com.fuyi.upms.alone.service;

import com.fuyi.upms.dao.entity.UpmsUser;
import com.fuyi.upms.dao.entity.UpmsUserExample;

import java.util.List;

public interface UserService {

    /**
     * 根据条件查询记录并按页码分页
     * @param example 条件
     * @param pageNum 页数
     * @param pageSize 每页记录数
     * @return
     */
    List<UpmsUser> selectByExampleForStartPage(UpmsUserExample example, Integer pageNum, Integer pageSize);

    /**
     * 根据条件查询记录并按最后记录数分页
     * @param example 条件
     * @param offset 跳过数量
     * @param limit 查询数量
     */
    List<UpmsUser> selectByExampleForOffsetPage(UpmsUserExample example, Integer offset, Integer limit);

    /**
     * 根据条件查询记录数量
     */
    int countByExample(UpmsUserExample example);
}
