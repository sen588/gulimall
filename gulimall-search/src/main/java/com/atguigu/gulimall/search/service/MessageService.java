package com.atguigu.gulimall.search.service;

import com.atguigu.gulimall.search.vo.SearchParam;
import com.atguigu.gulimall.search.vo.SearchResponse;

public interface MessageService {

    /**
     * 商品检索功能实现
     * @param params
     * @return
     */
    SearchResponse getSearchParamById(SearchParam params);
}
