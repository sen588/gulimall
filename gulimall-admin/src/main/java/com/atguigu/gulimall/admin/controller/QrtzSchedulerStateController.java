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

import com.atguigu.gulimall.wms.entity.QrtzSchedulerStateEntity;
import com.atguigu.gulimall.wms.service.QrtzSchedulerStateService;




/**
 * 
 *
 * @author ÀîÉ­
 * @email 2050531502@qq.com
 * @date 2019-08-01 19:28:52
 */
@Api(tags = " 管理")
@RestController
@RequestMapping("wms/qrtzschedulerstate")
public class QrtzSchedulerStateController {
    @Autowired
    private QrtzSchedulerStateService qrtzSchedulerStateService;

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('wms:qrtzschedulerstate:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = qrtzSchedulerStateService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{schedName}")
    @PreAuthorize("hasAuthority('wms:qrtzschedulerstate:info')")
    public Resp<QrtzSchedulerStateEntity> info(@PathVariable("schedName") String schedName){
		QrtzSchedulerStateEntity qrtzSchedulerState = qrtzSchedulerStateService.getById(schedName);

        return Resp.ok(qrtzSchedulerState);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('wms:qrtzschedulerstate:save')")
    public Resp<Object> save(@RequestBody QrtzSchedulerStateEntity qrtzSchedulerState){
		qrtzSchedulerStateService.save(qrtzSchedulerState);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('wms:qrtzschedulerstate:update')")
    public Resp<Object> update(@RequestBody QrtzSchedulerStateEntity qrtzSchedulerState){
		qrtzSchedulerStateService.updateById(qrtzSchedulerState);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('wms:qrtzschedulerstate:delete')")
    public Resp<Object> delete(@RequestBody String[] schedNames){
		qrtzSchedulerStateService.removeByIds(Arrays.asList(schedNames));

        return Resp.ok(null);
    }

}
