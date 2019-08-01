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

import com.atguigu.gulimall.wms.entity.SysUserTokenEntity;
import com.atguigu.gulimall.wms.service.SysUserTokenService;




/**
 * 系统用户Token
 *
 * @author ÀîÉ­
 * @email 2050531502@qq.com
 * @date 2019-08-01 19:28:52
 */
@Api(tags = "系统用户Token 管理")
@RestController
@RequestMapping("wms/sysusertoken")
public class SysUserTokenController {
    @Autowired
    private SysUserTokenService sysUserTokenService;

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('wms:sysusertoken:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = sysUserTokenService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{userId}")
    @PreAuthorize("hasAuthority('wms:sysusertoken:info')")
    public Resp<SysUserTokenEntity> info(@PathVariable("userId") Long userId){
		SysUserTokenEntity sysUserToken = sysUserTokenService.getById(userId);

        return Resp.ok(sysUserToken);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('wms:sysusertoken:save')")
    public Resp<Object> save(@RequestBody SysUserTokenEntity sysUserToken){
		sysUserTokenService.save(sysUserToken);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('wms:sysusertoken:update')")
    public Resp<Object> update(@RequestBody SysUserTokenEntity sysUserToken){
		sysUserTokenService.updateById(sysUserToken);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('wms:sysusertoken:delete')")
    public Resp<Object> delete(@RequestBody Long[] userIds){
		sysUserTokenService.removeByIds(Arrays.asList(userIds));

        return Resp.ok(null);
    }

}
