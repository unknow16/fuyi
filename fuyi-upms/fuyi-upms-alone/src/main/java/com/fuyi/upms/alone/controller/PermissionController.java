package com.fuyi.upms.alone.controller;

import com.fuyi.upms.alone.auth.UserDetailsImpl;
import com.fuyi.upms.alone.bean.RespBean;
import com.fuyi.upms.alone.service.PermissionService;
import com.fuyi.upms.dao.entity.UpmsPermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName PermissionController
 * @Description TODO
 * @Author fuyi
 * @Date 2018/11/23 15:01
 * @Version 1.0
 */
@RestController
@Api(value = "权限管理", description = "权限管理")
@RequestMapping("/manage/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @ApiOperation(value = "获取用户权限")
    @RequestMapping(value = "/initMenu", method = RequestMethod.POST)
    public Object selectPermissionByUserRoleId() {
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Object permissionList = permissionService.selectPermissionByUserRoleId(principal.getRoles());
        return RespBean.ok("获取用户权限成功", permissionList);
    }

}
