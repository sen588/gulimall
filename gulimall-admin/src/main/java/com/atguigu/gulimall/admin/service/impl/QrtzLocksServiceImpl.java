package com.atguigu.gulimall.wms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.Query;
import com.atguigu.gulimall.commons.bean.QueryCondition;

import com.atguigu.gulimall.wms.dao.QrtzLocksDao;
import com.atguigu.gulimall.wms.entity.QrtzLocksEntity;
import com.atguigu.gulimall.wms.service.QrtzLocksService;


@Service("qrtzLocksService")
public class QrtzLocksServiceImpl extends ServiceImpl<QrtzLocksDao, QrtzLocksEntity> implements QrtzLocksService {

    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<QrtzLocksEntity> page = this.page(
                new Query<QrtzLocksEntity>().getPage(params),
                new QueryWrapper<QrtzLocksEntity>()
        );

        return new PageVo(page);
    }

}