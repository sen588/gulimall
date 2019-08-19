package com.atguigu.gulimall.pms.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GuliCache {

    String pexfix() default "cache";
}
