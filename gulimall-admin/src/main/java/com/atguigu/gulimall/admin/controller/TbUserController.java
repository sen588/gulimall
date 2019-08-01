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

import com.atguigu.gulimall.wms.entity.TbUserEntity;
import com.atguigu.gulimall.wms.service.TbUserService;




/**
 * 用户
 *
 * @author ÀîÉ­
 * @email 2050531502@qq.com
 * @date 2019-08-01 19:28:52
 */
@Api(tags = "用户 管理")
@RestController
@RequestMapping("wms/tbuser")
public class TbUserController {
    @Autowired
    private TbUserService tbUserService;

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('wms:tbuser:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = tbUserService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{userId}")
    @PreAuthorize("hasAuthority('wms:tbuser:info')")
    public Resp<TbUserEntity> info(@PathVariable("userId") Long userId){
		TbUserEntity tbUser = tbUserService.getById(userId);

        return Resp.ok(tbUser);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('wms:tbuser:save')")
    public Resp<Object> save(@RequestBody TbUserEntity tbUser){
		tbUserService.save(tbUser);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('wms:tbuser:update')")
    public Resp<Object> update(@RequestBody TbUserEntity tbUser){
		tbUserService.updateById(tbUser);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('wms:tbuser:delete')")
    public Resp<Object> delete(@RequestBody Long[] userIds){
		tbUserService.removeByIds(Arrays.asList(userIds));

        return Resp.ok(null);
    }

}
