package com.atguigu.gulimall.wms.controller;

import java.util.Arrays;
import java.util.Map;


import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.QueryCondition;
import com.atguigu.gulimall.commons.bean.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.atguigu.gulimall.wms.entity.ScheduleJobLogEntity;
import com.atguigu.gulimall.wms.service.ScheduleJobLogService;




/**
 * 定时任务日志
 *
 * @author ÀîÉ­
 * @email 2050531502@qq.com
 * @date 2019-08-01 19:28:52
 */
@Api(tags = "定时任务日志 管理")
@RestController
@RequestMapping("wms/schedulejoblog")
public class ScheduleJobLogController {
    @Autowired
    private ScheduleJobLogService scheduleJobLogService;

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('wms:schedulejoblog:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = scheduleJobLogService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{logId}")
    @PreAuthorize("hasAuthority('wms:schedulejoblog:info')")
    public Resp<ScheduleJobLogEntity> info(@PathVariable("logId") Long logId){
		ScheduleJobLogEntity scheduleJobLog = scheduleJobLogService.getById(logId);

        return Resp.ok(scheduleJobLog);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('wms:schedulejoblog:save')")
    public Resp<Object> save(@RequestBody ScheduleJobLogEntity scheduleJobLog){
		scheduleJobLogService.save(scheduleJobLog);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('wms:schedulejoblog:update')")
    public Resp<Object> update(@RequestBody ScheduleJobLogEntity scheduleJobLog){
		scheduleJobLogService.updateById(scheduleJobLog);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('wms:schedulejoblog:delete')")
    public Resp<Object> delete(@RequestBody Long[] logIds){
		scheduleJobLogService.removeByIds(Arrays.asList(logIds));

        return Resp.ok(null);
    }

}
