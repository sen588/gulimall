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

import com.atguigu.gulimall.wms.entity.SysOssEntity;
import com.atguigu.gulimall.wms.service.SysOssService;




/**
 * 文件上传
 *
 * @author ÀîÉ­
 * @email 2050531502@qq.com
 * @date 2019-08-01 19:28:52
 */
@Api(tags = "文件上传 管理")
@RestController
@RequestMapping("wms/sysoss")
public class SysOssController {
    @Autowired
    private SysOssService sysOssService;

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('wms:sysoss:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = sysOssService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('wms:sysoss:info')")
    public Resp<SysOssEntity> info(@PathVariable("id") Long id){
		SysOssEntity sysOss = sysOssService.getById(id);

        return Resp.ok(sysOss);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('wms:sysoss:save')")
    public Resp<Object> save(@RequestBody SysOssEntity sysOss){
		sysOssService.save(sysOss);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('wms:sysoss:update')")
    public Resp<Object> update(@RequestBody SysOssEntity sysOss){
		sysOssService.updateById(sysOss);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('wms:sysoss:delete')")
    public Resp<Object> delete(@RequestBody Long[] ids){
		sysOssService.removeByIds(Arrays.asList(ids));

        return Resp.ok(null);
    }

}
