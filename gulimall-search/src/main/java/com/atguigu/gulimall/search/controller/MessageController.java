package com.atguigu.gulimall.search.controller;

import com.atguigu.gulimall.search.service.MessageService;
import com.atguigu.gulimall.search.vo.SearchParam;
import com.atguigu.gulimall.search.vo.SearchResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ApiOperation("商品通过es实现检索功能")
@RestController
@RequestMapping("search/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @ApiOperation("商品检索")
    @GetMapping("/commodity")
    public SearchResponse searchCommodity(SearchParam params)
    {
        SearchResponse response = messageService.getSearchParamById(params);
        return response;
    }
}
