package com.fuyi.upms.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fuyi.common.base.BaseResult;
import com.fuyi.upms.rpc.api.IUpmsOrganizationService;
import com.fuyi.upms.rpc.entity.UpmsOrganization;
import com.fuyi.upms.rpc.entity.UpmsOrganizationExample;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "组织管理", description = "组织管理")
@RestController
@RequestMapping("/manage/organization")
public class OrganizationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);

    @Reference
    private IUpmsOrganizationService organizationService;

    @ApiOperation(value = "新增组织")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(@RequestBody UpmsOrganization upmsOrganization) {
        long time = System.currentTimeMillis();
        upmsOrganization.setCtime(time);
        int count = organizationService.insertSelective(upmsOrganization);
        return BaseResult.ok("组织新增成功", count);
    }

    @ApiOperation(value = "删除组织")
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.GET)
    public Object delete(@PathVariable String ids) {
        int count = organizationService.deleteByPrimaryKeys(ids);
        return BaseResult.ok("组织删除成功", count);
    }

    @ApiOperation(value = "更新组织")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public Object delete(@PathVariable int id, @RequestBody UpmsOrganization upmsOrganization) {
        upmsOrganization.setOrganizationId(id);
        int count = organizationService.updateByPrimaryKeySelective(upmsOrganization);
        return BaseResult.ok("组织更新成功", count);
    }

    @ApiOperation(value = "组织列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "pageNum") int pageNum,
            @RequestParam(required = false, defaultValue = "10", value = "pageSize") int pageSize,
            @RequestParam(required = false, defaultValue = "", value = "query") String query,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UpmsOrganizationExample upmsOrganizationExample = new UpmsOrganizationExample();
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            upmsOrganizationExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(query)) {
            upmsOrganizationExample.or()
                    .andNameLike("%" + query + "%");
            upmsOrganizationExample.or()
                    .andDescriptionLike("%" + query + "%");
        }
        List<UpmsOrganization> rows = organizationService.selectByExampleForStartPage(upmsOrganizationExample, pageNum, pageSize);
        long total = organizationService.countByExample(upmsOrganizationExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }
}
