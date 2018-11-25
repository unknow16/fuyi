package com.fuyi.upms.alone.service;

import com.fuyi.framework.api.base.BaseService;
import com.fuyi.upms.dao.entity.UpmsUser;
import com.fuyi.upms.dao.entity.UpmsUserExample;

import java.util.List;

public interface UserService extends BaseService<UpmsUser, UpmsUserExample> {

    /**
     * 新增用户
     * @param upmsUser
     * @return
     */
    UpmsUser createUser(UpmsUser upmsUser);
}
