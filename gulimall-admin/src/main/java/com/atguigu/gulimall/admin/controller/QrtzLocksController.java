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

import com.atguigu.gulimall.wms.entity.QrtzLocksEntity;
import com.atguigu.gulimall.wms.service.QrtzLocksService;




/**
 * 
 *
 * @author ÀîÉ­
 * @email 2050531502@qq.com
 * @date 2019-08-01 19:28:52
 */
@Api(tags = " 管理")
@RestController
@RequestMapping("wms/qrtzlocks")
public class QrtzLocksController {
    @Autowired
    private QrtzLocksService qrtzLocksService;

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('wms:qrtzlocks:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = qrtzLocksService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{schedName}")
    @PreAuthorize("hasAuthority('wms:qrtzlocks:info')")
    public Resp<QrtzLocksEntity> info(@PathVariable("schedName") String schedName){
		QrtzLocksEntity qrtzLocks = qrtzLocksService.getById(schedName);

        return Resp.ok(qrtzLocks);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('wms:qrtzlocks:save')")
    public Resp<Object> save(@RequestBody QrtzLocksEntity qrtzLocks){
		qrtzLocksService.save(qrtzLocks);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('wms:qrtzlocks:update')")
    public Resp<Object> update(@RequestBody QrtzLocksEntity qrtzLocks){
		qrtzLocksService.updateById(qrtzLocks);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('wms:qrtzlocks:delete')")
    public Resp<Object> delete(@RequestBody String[] schedNames){
		qrtzLocksService.removeByIds(Arrays.asList(schedNames));

        return Resp.ok(null);
    }

}
