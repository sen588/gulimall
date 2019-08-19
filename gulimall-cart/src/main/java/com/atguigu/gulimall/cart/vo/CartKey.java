package com.atguigu.gulimall.cart.vo;

import lombok.Data;

@Data
public class CartKey {
    private String key;
    private boolean login;
    private boolean temp;
    private boolean merge;
}
