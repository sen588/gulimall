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

import com.atguigu.gulimall.wms.entity.ScheduleJobEntity;
import com.atguigu.gulimall.wms.service.ScheduleJobService;




/**
 * 定时任务
 *
 * @author ÀîÉ­
 * @email 2050531502@qq.com
 * @date 2019-08-01 19:28:52
 */
@Api(tags = "定时任务 管理")
@RestController
@RequestMapping("wms/schedulejob")
public class ScheduleJobController {
    @Autowired
    private ScheduleJobService scheduleJobService;

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('wms:schedulejob:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = scheduleJobService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{jobId}")
    @PreAuthorize("hasAuthority('wms:schedulejob:info')")
    public Resp<ScheduleJobEntity> info(@PathVariable("jobId") Long jobId){
		ScheduleJobEntity scheduleJob = scheduleJobService.getById(jobId);

        return Resp.ok(scheduleJob);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('wms:schedulejob:save')")
    public Resp<Object> save(@RequestBody ScheduleJobEntity scheduleJob){
		scheduleJobService.save(scheduleJob);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('wms:schedulejob:update')")
    public Resp<Object> update(@RequestBody ScheduleJobEntity scheduleJob){
		scheduleJobService.updateById(scheduleJob);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('wms:schedulejob:delete')")
    public Resp<Object> delete(@RequestBody Long[] jobIds){
		scheduleJobService.removeByIds(Arrays.asList(jobIds));

        return Resp.ok(null);
    }

}
