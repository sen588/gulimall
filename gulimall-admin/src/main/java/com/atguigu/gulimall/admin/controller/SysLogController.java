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

import com.atguigu.gulimall.wms.entity.SysLogEntity;
import com.atguigu.gulimall.wms.service.SysLogService;




/**
 * 系统日志
 *
 * @author ÀîÉ­
 * @email 2050531502@qq.com
 * @date 2019-08-01 19:28:52
 */
@Api(tags = "系统日志 管理")
@RestController
@RequestMapping("wms/syslog")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('wms:syslog:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = sysLogService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('wms:syslog:info')")
    public Resp<SysLogEntity> info(@PathVariable("id") Long id){
		SysLogEntity sysLog = sysLogService.getById(id);

        return Resp.ok(sysLog);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('wms:syslog:save')")
    public Resp<Object> save(@RequestBody SysLogEntity sysLog){
		sysLogService.save(sysLog);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('wms:syslog:update')")
    public Resp<Object> update(@RequestBody SysLogEntity sysLog){
		sysLogService.updateById(sysLog);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('wms:syslog:delete')")
    public Resp<Object> delete(@RequestBody Long[] ids){
		sysLogService.removeByIds(Arrays.asList(ids));

        return Resp.ok(null);
    }

}
