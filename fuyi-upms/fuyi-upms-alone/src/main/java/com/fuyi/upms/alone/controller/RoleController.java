package com.fuyi.upms.alone.controller;

import com.fuyi.upms.alone.auth.UserDetailsImpl;
import com.fuyi.upms.alone.bean.RespBean;
import com.fuyi.upms.alone.service.RoleService;
import com.fuyi.upms.dao.entity.UpmsRole;
import com.fuyi.upms.dao.entity.UpmsRoleExample;
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


@Api(value = "角色管理", description = "角色管理")
@RestController
@RequestMapping("/manage/role")
public class RoleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "新增角色")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(@RequestBody UpmsRole upmsRole) {
        long time = System.currentTimeMillis();
        upmsRole.setCtime(time);
        upmsRole.setOrders(time);
        int count = roleService.insertSelective(upmsRole);
        return RespBean.ok("角色新增成功", count);
    }

    @ApiOperation(value = "删除角色")
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.GET)
    public Object delete(@PathVariable String ids) {
        int count = roleService.deleteByPrimaryKeys(ids);
        return RespBean.ok("角色删除成功", count);
    }

    @ApiOperation(value = "更新角色")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public Object delete(@PathVariable int id, @RequestBody UpmsRole upmsRole) {
        upmsRole.setRoleId(id);
        int count = roleService.updateByPrimaryKeySelective(upmsRole);
        return RespBean.ok("角色更新成功", count);
    }

    @ApiOperation(value = "角色列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "pageNum") int pageNum,
            @RequestParam(required = false, defaultValue = "10", value = "pageSize") int pageSize,
            @RequestParam(required = false, defaultValue = "", value = "query") String query,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UpmsRoleExample upmsRoleExample = new UpmsRoleExample();
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            upmsRoleExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(query)) {
            upmsRoleExample.or()
                    .andNameLike("%" + query + "%");
            upmsRoleExample.or()
                    .andTitleLike("%" + query + "%");
        }
        List<UpmsRole> rows = roleService.selectByExampleForStartPage(upmsRoleExample, pageNum, pageSize);
        long total = roleService.countByExample(upmsRoleExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "该角色拥有的权限")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.GET)
    public Object permissions(@PathVariable String id) {
        return null;
    }

}
