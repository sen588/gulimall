package com.atguigu.gulimall.ums.service;

import com.atguigu.gulimall.commons.exception.LoginPasswordException;
import com.atguigu.gulimall.commons.exception.LoginUsernameException;
import com.atguigu.gulimall.ums.vo.MemberLoginVo;
import com.atguigu.gulimall.ums.vo.MemberRegistVo;
import com.atguigu.gulimall.ums.vo.MemberRespVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gulimall.ums.entity.MemberEntity;
import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.QueryCondition;


/**
 * 会员
 *
 * @author ÀîÉ­
 * @email 2050531502@qq.com
 * @date 2019-08-01 19:45:16
 */
public interface MemberService extends IService<MemberEntity> {

    PageVo queryPage(QueryCondition params);

    int insertRegisterById(MemberRegistVo vo);

    MemberRespVo getLoginById(MemberLoginVo vo) throws LoginUsernameException, LoginPasswordException;
}

