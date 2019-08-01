package com.atguigu.gulimall.wms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.Query;
import com.atguigu.gulimall.commons.bean.QueryCondition;

import com.atguigu.gulimall.wms.dao.QrtzCalendarsDao;
import com.atguigu.gulimall.wms.entity.QrtzCalendarsEntity;
import com.atguigu.gulimall.wms.service.QrtzCalendarsService;


@Service("qrtzCalendarsService")
public class QrtzCalendarsServiceImpl extends ServiceImpl<QrtzCalendarsDao, QrtzCalendarsEntity> implements QrtzCalendarsService {

    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<QrtzCalendarsEntity> page = this.page(
                new Query<QrtzCalendarsEntity>().getPage(params),
                new QueryWrapper<QrtzCalendarsEntity>()
        );

        return new PageVo(page);
    }

}