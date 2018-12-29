package com.fuyi.upms.alone.controller;

import com.fuyi.framework.web.base.BaseResult;
import com.fuyi.upms.alone.bean.RespBean;
import com.fuyi.upms.alone.service.SystemService;
import com.fuyi.upms.dao.entity.UpmsSystem;
import com.fuyi.upms.dao.entity.UpmsSystemExample;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "系统管理", description = "系统管理")
@RestController
@RequestMapping("/upms/system")
public class SystemController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    private SystemService systemService;

    @ApiOperation(value = "新增系统")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(@RequestBody UpmsSystem upmsSystem) {
        long time = System.currentTimeMillis();
        upmsSystem.setCtime(time);
        upmsSystem.setOrders(time);
        int count = systemService.insertSelective(upmsSystem);
        return RespBean.ok("系统新增成功", count);
    }

    @ApiOperation(value = "删除系统")
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.GET)
    public Object delete(@PathVariable String ids) {
        int count = systemService.deleteByPrimaryKeys(ids);
        return RespBean.ok("系统删除成功", count);
    }

    @ApiOperation(value = "更新系统")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public Object delete(@PathVariable int id, @RequestBody UpmsSystem upmsSystem) {
        upmsSystem.setSystemId(id);
        int count = systemService.updateByPrimaryKeySelective(upmsSystem);
        return RespBean.ok("系统更新成功", count);
    }

    @ApiOperation(value = "系统列表")
    @RequestMapping(value = "/list")
    public BaseResult list(
            @RequestParam(required = false, defaultValue = "0", value = "pageNum") int pageNum,
            @RequestParam(required = false, defaultValue = "10", value = "pageSize") int pageSize,
            @RequestParam(required = false, defaultValue = "", value = "query") String query,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            upmsSystemExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(query)) {
            upmsSystemExample.or()
                    .andNameLike("%" + query + "%");
            upmsSystemExample.or()
                    .andTitleLike("%" + query + "%");
        }
        List<UpmsSystem> rows = systemService.selectByExampleForStartPage(upmsSystemExample, pageNum, pageSize);
        long total = systemService.countByExample(upmsSystemExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return BaseResult.ok(result);
    }
}
