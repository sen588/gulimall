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

import com.atguigu.gulimall.wms.entity.QrtzSimpropTriggersEntity;
import com.atguigu.gulimall.wms.service.QrtzSimpropTriggersService;




/**
 * 
 *
 * @author ÀîÉ­
 * @email 2050531502@qq.com
 * @date 2019-08-01 19:28:52
 */
@Api(tags = " 管理")
@RestController
@RequestMapping("wms/qrtzsimproptriggers")
public class QrtzSimpropTriggersController {
    @Autowired
    private QrtzSimpropTriggersService qrtzSimpropTriggersService;

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('wms:qrtzsimproptriggers:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = qrtzSimpropTriggersService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{schedName}")
    @PreAuthorize("hasAuthority('wms:qrtzsimproptriggers:info')")
    public Resp<QrtzSimpropTriggersEntity> info(@PathVariable("schedName") String schedName){
		QrtzSimpropTriggersEntity qrtzSimpropTriggers = qrtzSimpropTriggersService.getById(schedName);

        return Resp.ok(qrtzSimpropTriggers);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('wms:qrtzsimproptriggers:save')")
    public Resp<Object> save(@RequestBody QrtzSimpropTriggersEntity qrtzSimpropTriggers){
		qrtzSimpropTriggersService.save(qrtzSimpropTriggers);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('wms:qrtzsimproptriggers:update')")
    public Resp<Object> update(@RequestBody QrtzSimpropTriggersEntity qrtzSimpropTriggers){
		qrtzSimpropTriggersService.updateById(qrtzSimpropTriggers);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('wms:qrtzsimproptriggers:delete')")
    public Resp<Object> delete(@RequestBody String[] schedNames){
		qrtzSimpropTriggersService.removeByIds(Arrays.asList(schedNames));

        return Resp.ok(null);
    }

}
