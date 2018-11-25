package com.fuyi.upms.alone.controller;

import com.fuyi.upms.alone.auth.UserDetailsImpl;
import com.fuyi.upms.alone.bean.RespBean;
import com.fuyi.upms.alone.service.UserService;
import com.fuyi.upms.dao.entity.UpmsOrganization;
import com.fuyi.upms.dao.entity.UpmsRole;
import com.fuyi.upms.dao.entity.UpmsUser;
import com.fuyi.upms.dao.entity.UpmsUserExample;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "用户管理", description = "用户管理")
@RequestMapping("/manage/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService upmsUserService;

    @ApiOperation(value = "新增用户")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(@RequestBody UpmsUser upmsUser) {
        upmsUser = upmsUserService.createUser(upmsUser);
        if (null == upmsUser) {
            return RespBean.error("该用户名已经存在");
        }
        LOGGER.info("新增用户，主键：userId={}", upmsUser.getUserId());
        return RespBean.ok("用户新增成功", 1);
    }

    @ApiOperation(value = "删除用户")
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.GET)
    public Object delete(@PathVariable String ids) {
        int count = upmsUserService.deleteByPrimaryKeys(ids);
        LOGGER.info("删除用户，主键：userId={}", ids);
        return RespBean.ok("用户删除成功", count);
    }

    @ApiOperation(value = "更新用户")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public Object delete(@PathVariable int id, @RequestBody UpmsUser upmsUser) {
        // 不允许直接改密码
        upmsUser.setPassword(null);
        upmsUser.setUserId(id);
        int count = upmsUserService.updateByPrimaryKeySelective(upmsUser);
        LOGGER.info("更新用户，主键：userId={}", upmsUser.getUserId());
        return RespBean.ok("用户更新成功", count);
    }

    @ApiOperation(value = "用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "pageNum") int pageNum,
            @RequestParam(required = false, defaultValue = "10", value = "pageSize") int pageSize,
            @RequestParam(required = false, defaultValue = "", value = "query") String query,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UpmsUserExample upmsUserExample = new UpmsUserExample();
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            upmsUserExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(query)) {
            upmsUserExample.or()
                    .andRealnameLike("%" + query + "%");
            upmsUserExample.or()
                    .andUsernameLike("%" + query + "%");
        }
        List<UpmsUser> rows = upmsUserService.selectByExampleForStartPage(upmsUserExample, pageNum, pageSize);
        long total = upmsUserService.countByExample(upmsUserExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "用户角色")
    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public Object roles() {
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<UpmsRole> roles = upmsUserService.selectUserRoles(principal.getUserId());
        return roles;
    }

    @ApiOperation(value = "用户组织")
    @RequestMapping(value = "/orgs", method = RequestMethod.GET)
    public Object orgs() {
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<UpmsOrganization> organizations = upmsUserService.selectUserOrganizations(principal.getUserId());
        return organizations;
    }

    @ApiOperation(value = "用户权限")
    @RequestMapping(value = "/permissions", method = RequestMethod.GET)
    public Object permissions() {
        //UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return null;
    }
}
