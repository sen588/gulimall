package com.atguigu.gulimall.cart.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.gulimall.cart.feign.PmsFeignService;
import com.atguigu.gulimall.cart.feign.SmsFeignService;
import com.atguigu.gulimall.cart.service.CartService;
import com.atguigu.gulimall.cart.to.SkuCouponTo;
import com.atguigu.gulimall.cart.vo.*;
import com.atguigu.gulimall.commons.bean.Constant;
import com.atguigu.gulimall.commons.bean.Resp;
import com.atguigu.gulimall.commons.to.SkuInfoVo;
import com.atguigu.gulimall.commons.utils.GuliJwtUtils;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redisson;

    @Autowired
    @Qualifier("otherExecutor")
    private ThreadPoolExecutor executor;

    /**
     * 远程fengin
     */
    @Autowired
    private PmsFeignService pmsFeignService;
    
    @Autowired
    private SmsFeignService smsFeignService;


    @Override
    public CartVo checkCart(Long[] skuId, Integer status, String userKey, String authorization) {
        CartKey key = getKey(userKey, authorization);
        String keyKey = key.getKey();
        RMap<String, String> cart = redisson.getMap(Constant.CART_PREFIX + keyKey);
        if(skuId!=null && skuId.length>0)
        {
            for (Long sku : skuId) {
                String json = cart.get(sku.toString());
                CartItemVo cartItemVo = JSON.parseObject(json, CartItemVo.class);
                cartItemVo.setCheck(status==0 ? false : true);
                cart.put(sku.toString(), JSON.toJSONString(cartItemVo));
            }
        }
        List<CartItemVo> cartItems = getCartItems(keyKey);
        CartVo cartVo = new CartVo();
        cartVo.setItems(cartItems);
        return null;
    }

    @Override
    public CartVo updateCart(Long skuId, Integer num, String userKey, String authorization) {
        CartKey key = getKey(userKey, authorization);
        String keyKey = key.getKey();

        RMap<String, String> cart = redisson.getMap(Constant.CART_PREFIX + keyKey);

        String itemJson = cart.get(skuId.toString());
        CartItemVo itemVo = JSON.parseObject(itemJson, CartItemVo.class);
        itemVo.setNum(num);
        //修改购物车，覆盖redis数据；
        cart.put(skuId.toString(), JSON.toJSONString(itemVo));

        List<CartItemVo> cartItems = getCartItems(keyKey);
        CartVo cartVo = new CartVo();
        cartVo.setItems(cartItems);
        return cartVo;
    }


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
            CompletableFuture<Void> infoAsync = CompletableFuture.runAsync(() -> {
                //1、查询sku当前商品的详情；
                Resp<SkuInfoVo> sKuInfoForCart = pmsFeignService.getSKuInfoForCart(skuId);
                SkuInfoVo data = sKuInfoForCart.getData();
                //2、购物项
                BeanUtils.copyProperties(data, cartItemVo);
                cartItemVo.setNum(num);
            }, executor);

            //封装优惠信息
            CompletableFuture<Void> couponAsync = CompletableFuture.runAsync(() -> {
                //获取优惠券当前的基本信息
                Resp<List<SkuCouponTo>> coupons = smsFeignService.getCoupons(skuId);
                //To封装别人传来的数据
                List<SkuCouponTo> data = coupons.getData();
                //vo提取别人传来的数据里面有用的数据
                List<SkuCouponVo> vos = new ArrayList<>();
                if (data != null && data.size() > 0) {
                    for (SkuCouponTo datum : data) {
                        SkuCouponVo couponVo = new SkuCouponVo();
                        BeanUtils.copyProperties(datum, couponVo);
                        vos.add(couponVo);
                    }
                }
                cartItemVo.setCoupons(vos);
            }, executor);
            //封装商品的满减信息
            CompletableFuture<Void> reductionAsync = CompletableFuture.runAsync(() -> {
                Resp<List<SkuFullReductionVo>> redutions = smsFeignService.getRedutions(skuId);
                List<SkuFullReductionVo> data = redutions.getData();
                if (data != null && data.size() > 0) {
                    cartItemVo.setReductions(data);
                }
            }, executor);
            CompletableFuture<Void> future = CompletableFuture.allOf(infoAsync, couponAsync, reductionAsync);
            try {
                future.get();
            } catch (Exception e) {

            }
            //3、保存购物车数据
            cart.put(skuId.toString(), JSON.toJSONString(cartItemVo));
            vo = cartItemVo;
        }
        return vo;
    }

    /**
     * 不用传递前缀
     *
     * @param cartKey
     *
     * @return
     */
    private List<CartItemVo> getCartItems(String cartKey) {
        List<CartItemVo> vos = new ArrayList<>();
        //1、没登录获取临时购物车
        RMap<String, String> cart = redisson.getMap(Constant.CART_PREFIX + cartKey);
        Collection<String> values = cart.values();
        if (values != null && values.size() > 0) {
            for (String value : values) {
                CartItemVo itemVo = JSON.parseObject(value, CartItemVo.class);
                vos.add(itemVo);
            }
        }
        return vos;
    }
}
