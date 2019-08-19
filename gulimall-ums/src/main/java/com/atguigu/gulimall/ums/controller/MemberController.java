package com.atguigu.gulimall.ums.controller;

import java.util.Arrays;
import java.util.Map;


import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.QueryCondition;
import com.atguigu.gulimall.commons.bean.Resp;
import com.atguigu.gulimall.commons.exception.LoginPasswordException;
import com.atguigu.gulimall.commons.exception.LoginUsernameException;
import com.atguigu.gulimall.ums.vo.MemberLoginVo;
import com.atguigu.gulimall.ums.vo.MemberRegistVo;
import com.atguigu.gulimall.ums.vo.MemberRespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.atguigu.gulimall.ums.entity.MemberEntity;
import com.atguigu.gulimall.ums.service.MemberService;




/**
 * 会员
 *
 * @author ÀîÉ­
 * @email 2050531502@qq.com
 * @date 2019-08-01 19:45:16
 */
@Api(tags = "会员 管理")
@RestController
@RequestMapping("ums/member")
public class MemberController {
    @Autowired
    private MemberService memberService;


    @ApiOperation("用户登陆功能实现")
    @GetMapping("/login")
    public Resp<Object> login(MemberLoginVo vo)
    {
        MemberRespVo vos;
        try {
            vos = memberService.getLoginById(vo);
            return Resp.ok(vos);
        } catch (LoginUsernameException e) {
            return Resp.fail(e.getMessage());
        } catch (LoginPasswordException e) {
            return Resp.fail(e.getMessage());
        }
    }

    @ApiOperation("用户注册功能实现")
    @PostMapping("/register")
    public Resp<String> register(MemberRegistVo vo)
    {
        /**
         * -1 失败、0  成功、1 用户名重复、2 手机号码重复、3 电子邮件重复
         */
        switch (memberService.insertRegisterById(vo))
        {
            case 0 : return Resp.ok("恭喜你，注册成功。");
            case 1 : return Resp.fail("此用户名已存在！！！");
            case 2 : return Resp.fail("此手机号已存在！！！");
            case 3 : return Resp.fail("此电子邮已存在！！！");
        }
        return Resp.fail("注册信息错误，请重新注册！！！");
    }
    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ums:member:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = memberService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('ums:member:info')")
    public Resp<MemberEntity> info(@PathVariable("id") Long id){
		MemberEntity member = memberService.getById(id);

        return Resp.ok(member);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ums:member:save')")
    public Resp<Object> save(@RequestBody MemberEntity member){
		memberService.save(member);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('ums:member:update')")
    public Resp<Object> update(@RequestBody MemberEntity member){
		memberService.updateById(member);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('ums:member:delete')")
    public Resp<Object> delete(@RequestBody Long[] ids){
		memberService.removeByIds(Arrays.asList(ids));

        return Resp.ok(null);
    }

}
