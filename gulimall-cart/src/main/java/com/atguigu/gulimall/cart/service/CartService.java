package com.atguigu.gulimall.cart.service;

import com.atguigu.gulimall.cart.vo.CartVo;

public interface CartService {

    CartVo addToCartById(Long skuId, Integer num, String userKey, String authorization);

    CartVo getCartById(String userKey, String authorization);
}
