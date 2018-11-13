package com.fuyi.upms.rpc.api;

import com.fuyi.framework.api.base.BaseService;
import com.fuyi.upms.dao.entity.UpmsUser;
import com.fuyi.upms.dao.entity.UpmsUserExample;

public interface UpmsUserService extends BaseService<UpmsUser, UpmsUserExample> {

    UpmsUser createUser(UpmsUser upmsUser);

}
