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

import com.atguigu.gulimall.wms.entity.QrtzSimpleTriggersEntity;
import com.atguigu.gulimall.wms.service.QrtzSimpleTriggersService;




/**
 * 
 *
 * @author ÀîÉ­
 * @email 2050531502@qq.com
 * @date 2019-08-01 19:28:52
 */
@Api(tags = " 管理")
@RestController
@RequestMapping("wms/qrtzsimpletriggers")
public class QrtzSimpleTriggersController {
    @Autowired
    private QrtzSimpleTriggersService qrtzSimpleTriggersService;

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('wms:qrtzsimpletriggers:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = qrtzSimpleTriggersService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{schedName}")
    @PreAuthorize("hasAuthority('wms:qrtzsimpletriggers:info')")
    public Resp<QrtzSimpleTriggersEntity> info(@PathVariable("schedName") String schedName){
		QrtzSimpleTriggersEntity qrtzSimpleTriggers = qrtzSimpleTriggersService.getById(schedName);

        return Resp.ok(qrtzSimpleTriggers);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('wms:qrtzsimpletriggers:save')")
    public Resp<Object> save(@RequestBody QrtzSimpleTriggersEntity qrtzSimpleTriggers){
		qrtzSimpleTriggersService.save(qrtzSimpleTriggers);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('wms:qrtzsimpletriggers:update')")
    public Resp<Object> update(@RequestBody QrtzSimpleTriggersEntity qrtzSimpleTriggers){
		qrtzSimpleTriggersService.updateById(qrtzSimpleTriggers);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('wms:qrtzsimpletriggers:delete')")
    public Resp<Object> delete(@RequestBody String[] schedNames){
		qrtzSimpleTriggersService.removeByIds(Arrays.asList(schedNames));

        return Resp.ok(null);
    }

}
