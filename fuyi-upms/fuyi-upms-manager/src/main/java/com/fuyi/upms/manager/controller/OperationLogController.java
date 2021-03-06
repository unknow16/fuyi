package com.fuyi.upms.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fuyi.upms.rpc.api.IUpmsOperationLogService;
import com.fuyi.upms.rpc.entity.UpmsLog;
import com.fuyi.upms.rpc.entity.UpmsLogExample;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "操作日志", description = "操作日志")
@RestController
@RequestMapping("/manage/log")
public class OperationLogController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperationLogController.class);

    @Reference
    private IUpmsOperationLogService operationLogService;

    @ApiOperation(value = "操作日志列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "pageNum") int pageNum,
            @RequestParam(required = false, defaultValue = "10", value = "pageSize") int pageSize,
            @RequestParam(required = false, defaultValue = "", value = "query") String query,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UpmsLogExample upmsLogExample = new UpmsLogExample();
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            upmsLogExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(query)) {
            upmsLogExample.or()
                    .andUsernameLike("%" + query + "%");
            upmsLogExample.or()
                    .andDescriptionLike("%" + query + "%");
        }
        List<UpmsLog> rows = operationLogService.selectByExampleForStartPage(upmsLogExample, pageNum, pageSize);
        long total = operationLogService.countByExample(upmsLogExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }
}
