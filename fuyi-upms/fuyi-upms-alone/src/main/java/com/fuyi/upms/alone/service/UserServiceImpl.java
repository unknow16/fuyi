package com.fuyi.upms.alone.service;

import com.fuyi.upms.dao.entity.UpmsUser;
import com.fuyi.upms.dao.entity.UpmsUserExample;
import com.fuyi.upms.dao.mapper.UpmsUserMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UpmsUserMapper upmsUserMapper;

    @Override
    public List<UpmsUser> selectByExampleForOffsetPage(UpmsUserExample example, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit, false);
        List<UpmsUser> upmsUsers = upmsUserMapper.selectByExample(example);
        return upmsUsers;
    }

    @Override
    public int countByExample(UpmsUserExample example) {
        int count = upmsUserMapper.countByExample(example);
        return count;
    }
}
