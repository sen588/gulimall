package com.atguigu.gulimall.cart.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.gulimall.cart.service.CartService;
import com.atguigu.gulimall.cart.vo.CartItemVo;
import com.atguigu.gulimall.cart.vo.CartKey;
import com.atguigu.gulimall.cart.vo.CartVo;
import com.atguigu.gulimall.commons.bean.Constant;
import com.atguigu.gulimall.commons.bean.Resp;
import com.atguigu.gulimall.commons.to.SkuInfoVo;
import com.atguigu.gulimall.commons.utils.GuliJwtUtils;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redisson;


    @Override
    public CartVo getCartById(String userKey, String authorization) {
        CartVo cartVo = new CartVo();
        CartKey cartKey = getKey(userKey, authorization);
        if (cartKey.isLogin())
        {
            //登陆了合并购物车
            mergeCart(userKey, Long.parseLong(cartKey.getKey()));
        }
        return cartVo;
    }

    /**
     * 获取到所有的购物车里面的购物项
     * @param skuId
     * @param num
     * @param userKey
     * @param authorization
     * @return
     */
    @Override
    public CartVo addToCartById(Long skuId, Integer num, String userKey, String authorization) {
        CartVo cartVo = new CartVo();
        List<CartItemVo> cartItemVos = new ArrayList<>();
        CartKey cartKey = getKey(userKey, authorization);
        //购物车在Redis中保存
        String key = Constant.CART_PREFIX + cartKey.getKey();

        //1、获取购物车
        RMap<String, Object> map = redisson.getMap(key);
        //判断如果两个都有需要合并购物车
        if (cartKey.isMerge() == true) {
            //如果需要合并。合并

        } else {
            //获取到所有的购物车里面的购物项
            Collection<Object> values = map.values();
            if (values != null && values.size() > 0) {
                for (Object value : values) {
                    String json = (String) value;
                    CartItemVo itemVo = JSON.parseObject(json, CartItemVo.class);
                    cartItemVos.add(itemVo);
                }
            }

        }
        cartVo.setItems(cartItemVos);
        return cartVo;
    }

    /**
     *  只要登录了返回的是用户id；
     *  没登录返回临时的购物车key
     * @param userKey
     * @param authorization
     * @return
     */
    private CartKey getKey(String userKey, String authorization) {
        CartKey cartKey = new CartKey();
        String key;
        if(!StringUtils.isEmpty(authorization))
        {
            //登陆了
            Map<String, Object> body = GuliJwtUtils.getJwtBody(authorization);
            Long id = Long.parseLong(body.get("id").toString());
            key = id + "";
            cartKey.setKey(key);
            cartKey.setLogin(true);
            if (!StringUtils.isEmpty(userKey)) {
                cartKey.setMerge(true);
            }
        }else {
            //第一次啥都没有
            if (!StringUtils.isEmpty(userKey)) {
                key = userKey;
                cartKey.setLogin(false);

                cartKey.setMerge(false);
            } else {
                key = UUID.randomUUID().toString().replace("-", "");
                cartKey.setLogin(false);
                cartKey.setMerge(false);
                cartKey.setTemp(true);//这是一个临时
            }
        }
        cartKey.setKey(key);
        return cartKey;
    }

    /**
     * 登陆成功后合并购物车
     * @param userKey
     * @param userId
     */
    private void mergeCart(String userKey, Long userId) {
        RMap<String, String> map = redisson.getMap(Constant.CART_PREFIX + userKey);

        Collection<String> values = map.values();
        if(values!=null && values.size()>0)
        {
            for (String value : values) {
                CartItemVo vos = JSON.parseObject(value, CartItemVo.class);
                //在线合并购物车
                addCartItemVo(vos.getSkuId(), vos.getNum(), userId.toString());
            }
        }
        //删除redis中的临时购物车
        redisTemplate.delete(Constant.CART_PREFIX + userKey);
    }

    //在线合并购物车
    private CartItemVo addCartItemVo(Long skuId, Integer num, String cartKey) {
        CartItemVo vo = null;
        RMap<String, String> cart = redisson.getMap(Constant.CART_PREFIX + cartKey);

        String item = cart.get(skuId.toString());
        if (!StringUtils.isEmpty(item)) {
            //购车之前有
            CartItemVo itemVo = JSON.parseObject(item, CartItemVo.class);
            itemVo.setNum(itemVo.getNum() + num);
            cart.put(skuId.toString(), JSON.toJSONString(itemVo));
            vo = itemVo;
        }else {
            CartItemVo cartItemVo = new CartItemVo();
            //1）、封装基本信息
           /* CompletableFuture<Void> infoAsync = CompletableFuture.runAsync(() -> {
                //1、查询sku当前商品的详情；
                Resp<SkuInfoVo> sKuInfoForCart = skuFeignService.getSKuInfoForCart(skuId);
                SkuInfoVo data = sKuInfoForCart.getData();
                //2、购物项
                BeanUtils.copyProperties(data, itemVo);
                itemVo.setNum(num);
            }, executor);*/


        }
        return vo;
    }
}
