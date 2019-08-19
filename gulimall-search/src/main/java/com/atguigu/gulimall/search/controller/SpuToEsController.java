package com.atguigu.gulimall.search.controller;

import com.atguigu.gulimall.commons.bean.Constant;
import com.atguigu.gulimall.commons.bean.Resp;
import com.atguigu.gulimall.commons.to.es.EsSkuVo;
import io.searchbox.client.JestClient;
import io.searchbox.core.Delete;
import io.searchbox.core.Index;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@ApiOperation("商品上架、下架功能")
@RestController
@RequestMapping("search/spu")
public class SpuToEsController {

    @Autowired
    private JestClient jestClient;

    /**
     * 远程es商品上架
     * @param vos
     * @return
     */
    @PostMapping("/up")
    public Resp<Object> spuUp(@RequestBody List<EsSkuVo> vos)
    {
        vos.forEach(vo -> {
            Index index = new Index.Builder(vo)
                    .index(Constant.ES_SPU_INDEX)
                    .type(Constant.ES_SPU_TYPE)
                    .id(vo.getId().toString())
                    .build();
            try {
                jestClient.execute(index);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return Resp.ok(null);
    }

    /**
     * 远程es商品下架
     * @param spuIds
     * @return
     */
    @GetMapping("/del")
    public Resp<Object> spuDel(@RequestParam(value = "spuIds") List<Long> spuIds)
    {
        spuIds.forEach(spuId -> {
            Delete delete = new Delete.Builder(spuId + "")
                    .index(Constant.ES_SPU_INDEX)
                    .type(Constant.ES_SPU_TYPE)
                    .build();
            try {
                jestClient.execute(delete);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return Resp.ok(null);
    }
}
