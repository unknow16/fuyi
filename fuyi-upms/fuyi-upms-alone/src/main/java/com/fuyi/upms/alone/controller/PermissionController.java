package com.fuyi.upms.alone.controller;

import com.fuyi.framework.api.base.BaseResult;
import com.fuyi.upms.alone.auth.UserDetailsImpl;
import com.fuyi.upms.alone.bean.RespBean;
import com.fuyi.upms.alone.service.PermissionService;
import com.fuyi.upms.dao.entity.UpmsPermission;
import com.fuyi.upms.dao.entity.UpmsPermissionExample;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PermissionController
 * @Description TODO
 * @Author fuyi
 * @Date 2018/11/23 15:01
 * @Version 1.0
 */
@RestController
@Api(value = "权限管理", description = "权限管理")
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @ApiOperation(value = "获取用户权限")
    @RequestMapping(value = "/initMenu", method = RequestMethod.GET)
    public BaseResult selectPermissionByUserRoleId() {
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Object permissionList = permissionService.selectPermissionByUserRoleId(principal.getRoles());
        return BaseResult.ok(permissionList);
    }

    @ApiOperation(value = "新增权限")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(@RequestBody UpmsPermission upmsPermission) {
        long time = System.currentTimeMillis();
        upmsPermission.setCtime(time);
        upmsPermission.setOrders(time);
        int count = permissionService.insertSelective(upmsPermission);
        return RespBean.ok("权限新增成功", count);
    }

    @ApiOperation(value = "删除权限")
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.GET)
    public Object delete(@PathVariable String ids) {
        int count = permissionService.deleteByPrimaryKeys(ids);
        return RespBean.ok("权限删除成功", count);
    }

    @ApiOperation(value = "更新权限")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public Object delete(@PathVariable int id, @RequestBody UpmsPermission upmsPermission) {
        int count = permissionService.updateByPrimaryKeySelective(upmsPermission);
        return RespBean.ok("权限更新成功", count);
    }

    @ApiOperation(value = "权限列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "pageNum") int pageNum,
            @RequestParam(required = false, defaultValue = "10", value = "pageSize") int pageSize,
            @RequestParam(required = false, defaultValue = "", value = "query") String query,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UpmsPermissionExample upmsPermissionExample = new UpmsPermissionExample();
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            upmsPermissionExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(query)) {
            upmsPermissionExample.or()
                    .andNameLike("%" + query + "%");
        }
        List<UpmsPermission> rows = permissionService.selectByExampleForStartPage(upmsPermissionExample, pageNum, pageSize);
        long total = permissionService.countByExample(upmsPermissionExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }
}
