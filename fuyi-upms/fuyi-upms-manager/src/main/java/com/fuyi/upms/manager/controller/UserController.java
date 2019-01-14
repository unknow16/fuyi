package com.fuyi.upms.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fuyi.common.base.BaseResult;
import com.fuyi.upms.manager.auth.UserDetailsImpl;
import com.fuyi.upms.rpc.api.IUpmsUserService;
import com.fuyi.upms.rpc.entity.UpmsOrganization;
import com.fuyi.upms.rpc.entity.UpmsRole;
import com.fuyi.upms.rpc.entity.UpmsUser;
import com.fuyi.upms.rpc.entity.UpmsUserExample;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "用户管理", description = "用户管理")
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Reference
    private IUpmsUserService upmsUserService;

    @ApiOperation(value = "根据token获取用户信息")
    @RequestMapping(value = "/getUserInfo")
    public BaseResult getUserInfo() {
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return BaseResult.ok(principal);
    }

    @ApiOperation(value = "新增用户")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public BaseResult create(@RequestBody UpmsUser upmsUser) {
        //String salt = UUID.randomUUID().toString().replaceAll("-", "");
        //upmsUser.setSalt(salt);
        //upmsUser.setPassword(MD5Util.md5(upmsUser.getPassword() + upmsUser.getSalt()));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        upmsUser.setPassword(encoder.encode(upmsUser.getPassword()));
        upmsUser = upmsUserService.createUser(upmsUser);
        if (null == upmsUser) {
            return BaseResult.ok("该用户名已经存在");
        }
        LOGGER.info("新增用户，主键：userId={}", upmsUser.getUserId());
        return BaseResult.ok("用户新增成功", 1);
    }

    @ApiOperation(value = "删除用户")
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.GET)
    public BaseResult delete(@PathVariable String ids) {
        int count = upmsUserService.deleteByPrimaryKeys(ids);
        LOGGER.info("删除用户，主键：userId={}", ids);
        return BaseResult.ok("用户删除成功", count);
    }

    @ApiOperation(value = "更新用户")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public BaseResult delete(@PathVariable int id, @RequestBody UpmsUser upmsUser) {
        // 不允许直接改密码
        upmsUser.setPassword(null);
        upmsUser.setUserId(id);
        int count = upmsUserService.updateByPrimaryKeySelective(upmsUser);
        LOGGER.info("更新用户，主键：userId={}", upmsUser.getUserId());
        return BaseResult.ok("用户更新成功", count);
    }

    @ApiOperation(value = "用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public BaseResult list(
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
        return BaseResult.ok("获取用户列表成功！", result);
    }

    @ApiOperation(value = "用户角色")
    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public BaseResult roles() {
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<UpmsRole> roles = upmsUserService.selectUserRoles(principal.getUserId());
        return BaseResult.ok(roles);
    }

    @ApiOperation(value = "分配角色")
    @RequestMapping(value = "/assignRoles", method = RequestMethod.POST)
    public BaseResult assignRoles(@RequestBody Integer[] roleIds) {
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int count = upmsUserService.assignRoles(principal.getUserId(), roleIds);
        return BaseResult.ok("分配角色成功", count);
    }

    @ApiOperation(value = "用户组织")
    @RequestMapping(value = "/orgs", method = RequestMethod.GET)
    public BaseResult orgs() {
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<UpmsOrganization> organizations = upmsUserService.selectUserOrganizations(principal.getUserId());
        return BaseResult.ok(organizations);
    }


}
