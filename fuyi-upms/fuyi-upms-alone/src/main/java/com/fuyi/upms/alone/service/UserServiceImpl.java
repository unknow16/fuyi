package com.fuyi.upms.alone.service;

import com.fuyi.framework.service.annotation.BaseServiceAnnotation;
import com.fuyi.framework.service.base.BaseServiceImpl;
import com.fuyi.upms.dao.entity.UpmsUser;
import com.fuyi.upms.dao.entity.UpmsUserExample;
import com.fuyi.upms.dao.mapper.UpmsUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@BaseServiceAnnotation
public class UserServiceImpl extends BaseServiceImpl<UpmsUserMapper, UpmsUser, UpmsUserExample> implements UserService {

    @Autowired
    private UpmsUserMapper upmsUserMapper;


    @Override
    public UpmsUser createUser(UpmsUser upmsUser) {
        UpmsUserExample upmsUserExample = new UpmsUserExample();
        upmsUserExample.createCriteria().andUsernameEqualTo(upmsUser.getUsername());
        List<UpmsUser> upmsUsers = upmsUserMapper.selectByExample(upmsUserExample);
        if (upmsUsers != null && upmsUsers.size() > 0) {
            return null;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        long time = System.currentTimeMillis();

        //String salt = UUID.randomUUID().toString().replaceAll("-", "");
        //upmsUser.setSalt(salt);
        //upmsUser.setPassword(MD5Util.md5(upmsUser.getPassword() + upmsUser.getSalt()));
        upmsUser.setPassword(encoder.encode(upmsUser.getPassword()));
        upmsUser.setCtime(time);
        upmsUserMapper.insertSelective(upmsUser);
        return upmsUser;
    }
}
