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

import com.atguigu.gulimall.wms.entity.QrtzCronTriggersEntity;
import com.atguigu.gulimall.wms.service.QrtzCronTriggersService;




/**
 * 
 *
 * @author ÀîÉ­
 * @email 2050531502@qq.com
 * @date 2019-08-01 19:28:52
 */
@Api(tags = " 管理")
@RestController
@RequestMapping("wms/qrtzcrontriggers")
public class QrtzCronTriggersController {
    @Autowired
    private QrtzCronTriggersService qrtzCronTriggersService;

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('wms:qrtzcrontriggers:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = qrtzCronTriggersService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{schedName}")
    @PreAuthorize("hasAuthority('wms:qrtzcrontriggers:info')")
    public Resp<QrtzCronTriggersEntity> info(@PathVariable("schedName") String schedName){
		QrtzCronTriggersEntity qrtzCronTriggers = qrtzCronTriggersService.getById(schedName);

        return Resp.ok(qrtzCronTriggers);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('wms:qrtzcrontriggers:save')")
    public Resp<Object> save(@RequestBody QrtzCronTriggersEntity qrtzCronTriggers){
		qrtzCronTriggersService.save(qrtzCronTriggers);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('wms:qrtzcrontriggers:update')")
    public Resp<Object> update(@RequestBody QrtzCronTriggersEntity qrtzCronTriggers){
		qrtzCronTriggersService.updateById(qrtzCronTriggers);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('wms:qrtzcrontriggers:delete')")
    public Resp<Object> delete(@RequestBody String[] schedNames){
		qrtzCronTriggersService.removeByIds(Arrays.asList(schedNames));

        return Resp.ok(null);
    }

}
