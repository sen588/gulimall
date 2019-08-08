package com.atguigu.gulimall.sms.dao;

import com.atguigu.gulimall.commons.to.SkuSaleInfoTo;
import com.atguigu.gulimall.sms.entity.SkuBoundsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品sku积分设置
 * 
 * @author ÀîÉ­
 * @email 2050531502@qq.com
 * @date 2019-08-01 19:42:39
 */
@Mapper
public interface SkuBoundsDao extends BaseMapper<SkuBoundsEntity> {

}
