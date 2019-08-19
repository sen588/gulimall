package com.atguigu.gulimall.pms.controller.api;

import com.atguigu.gulimall.commons.bean.Resp;
import com.atguigu.gulimall.pms.service.ItemService;
import com.atguigu.gulimall.pms.vo.SkuItemDetailVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "前端三级显示分类")
@RestController
@RequestMapping("api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/{skuId}.html")
    public Resp<SkuItemDetailVo> skuDetail(@PathVariable("skuId") Long skuId)
    {
        SkuItemDetailVo vos = itemService.getSkuDetailById(skuId);
        return Resp.ok(vos);
    }
}
