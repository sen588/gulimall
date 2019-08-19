package com.atguigu.gulimall.cart.controller;

import com.atguigu.gulimall.cart.service.CartService;
import com.atguigu.gulimall.cart.vo.CartVo;
import com.atguigu.gulimall.commons.bean.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

@Api(tags = "购物车")
@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    @Qualifier("otherExecutor")
    private ThreadPoolExecutor executor;


    /**
     * 可以在业务运行期间通过运维控制平台，关闭一些资源，释放
     * @return
     */
    @GetMapping("/stop/other")
    public Resp<Object> closeThreadPool()
    {
        executor.getActiveCount();
        executor.getCorePoolSize();
        executor.shutdown();
        Map<String,Object> map = new HashMap<>();
        map.put("closeQueue",executor.getQueue().size());
        map.put("waitActiveCount",executor.getActiveCount());
        return Resp.ok(map);
    }

    /**
     * 选中/不选中购物车
     * @param skuId
     * @param userKey
     * @param status
     * @param authorization
     * @return
     */
    @ApiOperation("选中/不选中购物车")
    @PostMapping("/check")
    public Resp<CartVo> checkCart(@RequestParam("skuIds") Long[] skuId, String userKey,
                                  @RequestParam("status") Integer status,
                                  @RequestHeader(name = "Authorization",required = false) String authorization)
    {
        CartVo cartVo = cartService.checkCart(skuId,status,userKey,authorization);
        return Resp.ok(cartVo);
    }
    /**
     * 更新购物车商品数量
     * @param skuId
     * @param userKey
     * @param num
     * @param authorization
     * @return
     */
    @ApiOperation("更新购物车商品数量")
    @PostMapping("/update")
    public Resp<CartVo> updateCart(@RequestParam(name = "skuId") Long skuId ,String userKey ,
                                   @RequestParam(name = "num",defaultValue = "1") Integer num ,
                                   @RequestHeader(name = "Authorization",required = false) String authorization)
    {
        CartVo cartVo = cartService.updateCart(skuId ,num ,userKey ,authorization);
        return Resp.ok(cartVo);
    }

    /**
     * 获取购物车中的数据
     * @param Authorization
     * @param userKey
     * @return
     */
    @ApiOperation("获取购物车中的数据")
    @GetMapping("/list")
    public Resp<Object> getCart(@RequestHeader(value = "Authorization" ,required = false)
                                String Authorization ,String userKey)
    {
        CartVo cartVo = cartService.getCartById(userKey, Authorization);
        return Resp.ok(cartVo);
    }

    /**
     * 将某个sku放入购物车
     * @param skuId
     * @param userKey
     * @param num
     * @param authorization
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @ApiOperation(" 将某个sku放入购物车")
    @PostMapping("/add")
    public Resp<Object> addToCart(@RequestParam(name = "skuId") Long skuId ,String userKey ,
                                  @RequestParam(name = "num",defaultValue = "1") Integer num ,
                                  @RequestHeader(name = "Authorization",required = false) String authorization) throws ExecutionException, InterruptedException
    {
        //判断是否登陆
        CartVo cartVo = cartService.addToCartById(skuId, num, userKey, authorization);
        HashMap<String, Object> map = new HashMap<>();
        map.put("userKey", cartVo.getUserKey());
        map.put("item", cartVo.getItems());
        return Resp.ok(map);
    }

}
