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

import com.atguigu.gulimall.wms.entity.SysCaptchaEntity;
import com.atguigu.gulimall.wms.service.SysCaptchaService;




/**
 * 系统验证码
 *
 * @author ÀîÉ­
 * @email 2050531502@qq.com
 * @date 2019-08-01 19:28:52
 */
@Api(tags = "系统验证码 管理")
@RestController
@RequestMapping("wms/syscaptcha")
public class SysCaptchaController {
    @Autowired
    private SysCaptchaService sysCaptchaService;

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('wms:syscaptcha:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = sysCaptchaService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{uuid}")
    @PreAuthorize("hasAuthority('wms:syscaptcha:info')")
    public Resp<SysCaptchaEntity> info(@PathVariable("uuid") String uuid){
		SysCaptchaEntity sysCaptcha = sysCaptchaService.getById(uuid);

        return Resp.ok(sysCaptcha);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('wms:syscaptcha:save')")
    public Resp<Object> save(@RequestBody SysCaptchaEntity sysCaptcha){
		sysCaptchaService.save(sysCaptcha);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('wms:syscaptcha:update')")
    public Resp<Object> update(@RequestBody SysCaptchaEntity sysCaptcha){
		sysCaptchaService.updateById(sysCaptcha);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('wms:syscaptcha:delete')")
    public Resp<Object> delete(@RequestBody String[] uuids){
		sysCaptchaService.removeByIds(Arrays.asList(uuids));

        return Resp.ok(null);
    }

}
