package com.atguigu.gulimall.ums.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.gulimall.commons.bean.Constant;
import com.atguigu.gulimall.commons.exception.LoginPasswordException;
import com.atguigu.gulimall.commons.exception.LoginUsernameException;
import com.atguigu.gulimall.commons.utils.GuliJwtUtils;
import com.atguigu.gulimall.ums.vo.MemberLoginVo;
import com.atguigu.gulimall.ums.vo.MemberRegistVo;
import com.atguigu.gulimall.ums.vo.MemberRespVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.Query;
import com.atguigu.gulimall.commons.bean.QueryCondition;

import com.atguigu.gulimall.ums.dao.MemberDao;
import com.atguigu.gulimall.ums.entity.MemberEntity;
import com.atguigu.gulimall.ums.service.MemberService;
import springfox.documentation.spring.web.json.Json;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<MemberEntity> page = this.page(
                new Query<MemberEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageVo(page);
    }

    /**
     * 用户注册
     * @param vo
     * @return
     */
    @Override
    public int insertRegisterById(MemberRegistVo vo) {
        /**
         * 1 用户名
         */
        MemberEntity username = baseMapper.selectOne(new QueryWrapper<MemberEntity>()
                                          .eq("username", vo.getUsername()));
        if(username != null) return 1;//用户名存在返回1
        /**
         * 2 手机号码
         */
        MemberEntity mobile = baseMapper.selectOne(new QueryWrapper<MemberEntity>()
                                              .eq("mobile", vo.getPhone()));
        if(mobile != null) return 2;//手机号码存在返回 2
        /**
         * 3 电子邮件
         */
        MemberEntity email = baseMapper.selectOne(new QueryWrapper<MemberEntity>()
                                             .eq("email", vo.getEmail()));
        if(email != null) return 3;//电子邮件存在返回 3
        /**
         * new对象
         */
        MemberEntity member = new MemberEntity();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        member.setUsername(vo.getUsername());
        member.setPassword(encoder.encode(vo.getPassword()));
        member.setMobile(vo.getPhone());
        member.setEmail(vo.getEmail());
        //插入操作
        int insert = baseMapper.insert(member);
        /**
         * 0  成功
         * -1 失败
         */
        return (insert ==1 ? 0 : -1);
    }

    /**
     * 用户登陆
     * @param vo
     * @return
     * @throws LoginUsernameException
     * @throws LoginPasswordException
     */
    @Override
    public MemberRespVo getLoginById(MemberLoginVo vo) throws LoginUsernameException, LoginPasswordException {
        MemberRespVo respVo = new MemberRespVo();
        QueryWrapper<MemberEntity> wrapper = new QueryWrapper<MemberEntity>()
                .or().eq("username", vo.getLoginacct())
                .or().eq("mobile", vo.getLoginacct())
                .or().eq("email", vo.getLoginacct());
        MemberEntity memberEntity = baseMapper.selectOne(wrapper);
        if(memberEntity == null){
            //用户名不存在
            throw new LoginUsernameException();
        }
        //MD5判断密码
        boolean matches = new BCryptPasswordEncoder().matches(vo.getPassword(), memberEntity.getPassword());
        if(matches)
        {
            String token = UUID.randomUUID().toString().replace("-", "");
            //将所有数据保存到redis中
            redisTemplate.opsForValue().set(Constant.LOGIN_USER_PREFIX + token, JSON.toJSONString(memberEntity), Constant.LOGIN_USER_TIMEOUT_MINUTES, TimeUnit.MINUTES);
            Map<String,Object> map = new HashMap<>();
            map.put("token",token);
            map.put("id",memberEntity.getId());
            //将我们在redis中的token做成jwt返回过去
            String jwt = GuliJwtUtils.buildJwt(map, null);
            BeanUtils.copyProperties(memberEntity,respVo);
            respVo.setToken(jwt);
            return respVo;
        }
        //密码输入有误返回 2
        throw new LoginPasswordException();
    }
}