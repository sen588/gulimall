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

import com.atguigu.gulimall.wms.entity.QrtzJobDetailsEntity;
import com.atguigu.gulimall.wms.service.QrtzJobDetailsService;




/**
 * 
 *
 * @author ÀîÉ­
 * @email 2050531502@qq.com
 * @date 2019-08-01 19:28:52
 */
@Api(tags = " 管理")
@RestController
@RequestMapping("wms/qrtzjobdetails")
public class QrtzJobDetailsController {
    @Autowired
    private QrtzJobDetailsService qrtzJobDetailsService;

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('wms:qrtzjobdetails:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = qrtzJobDetailsService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{schedName}")
    @PreAuthorize("hasAuthority('wms:qrtzjobdetails:info')")
    public Resp<QrtzJobDetailsEntity> info(@PathVariable("schedName") String schedName){
		QrtzJobDetailsEntity qrtzJobDetails = qrtzJobDetailsService.getById(schedName);

        return Resp.ok(qrtzJobDetails);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('wms:qrtzjobdetails:save')")
    public Resp<Object> save(@RequestBody QrtzJobDetailsEntity qrtzJobDetails){
		qrtzJobDetailsService.save(qrtzJobDetails);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('wms:qrtzjobdetails:update')")
    public Resp<Object> update(@RequestBody QrtzJobDetailsEntity qrtzJobDetails){
		qrtzJobDetailsService.updateById(qrtzJobDetails);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('wms:qrtzjobdetails:delete')")
    public Resp<Object> delete(@RequestBody String[] schedNames){
		qrtzJobDetailsService.removeByIds(Arrays.asList(schedNames));

        return Resp.ok(null);
    }

}
