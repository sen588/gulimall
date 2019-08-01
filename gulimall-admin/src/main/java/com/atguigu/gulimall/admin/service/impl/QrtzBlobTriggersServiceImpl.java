package com.atguigu.gulimall.wms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.Query;
import com.atguigu.gulimall.commons.bean.QueryCondition;

import com.atguigu.gulimall.wms.dao.QrtzBlobTriggersDao;
import com.atguigu.gulimall.wms.entity.QrtzBlobTriggersEntity;
import com.atguigu.gulimall.wms.service.QrtzBlobTriggersService;


@Service("qrtzBlobTriggersService")
public class QrtzBlobTriggersServiceImpl extends ServiceImpl<QrtzBlobTriggersDao, QrtzBlobTriggersEntity> implements QrtzBlobTriggersService {

    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<QrtzBlobTriggersEntity> page = this.page(
                new Query<QrtzBlobTriggersEntity>().getPage(params),
                new QueryWrapper<QrtzBlobTriggersEntity>()
        );

        return new PageVo(page);
    }

}