package com.atguigu.gulimall.pms.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.atguigu.gulimall.commons.bean.Resp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RefreshScope
@RestController
@RequestMapping("/pms/oss/")
public class PmsOosController {

    //请填写您的AccessKeyId
    @Value("${accessId}")
    private String accessId;

    //请填写您的AccessKeySecret
    @Value("${accessKey}")
    private String accessKey;

    //请填写您的 endpoint
    @Value("${endpoint}")
    private String endpoint;

    //请填写您的 bucketname
    @Value("${bucket}")
    private String bucket;

    /*
    callbackUrl为上传回调服务器的URL，请将下面的IP和Port配置为您自己的真实信息
     */
    @GetMapping("policy")
    public Resp<Object> osspolicy() throws UnsupportedEncodingException {
        //host的格式为 bucketname.endpoint
        String host = "https://" + bucket + "." + endpoint;
        String dir = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        OSSClient client = new OSSClient(endpoint, accessId, accessKey);

        long expireTime = 30;
        long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
        Date expiration = new Date(expireEndTime);
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

        String postPolicy = client.generatePostPolicy(expiration, policyConds);
        byte[] binaryData = postPolicy.getBytes("utf-8");
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = client.calculatePostSignature(postPolicy);

        Map<String, String> respMap = new LinkedHashMap<String, String>();
        respMap.put("accessid", accessId);
        respMap.put("policy", encodedPolicy);
        respMap.put("signature", postSignature);
        respMap.put("dir", dir);//给前端指定上传到阿里云的那个文件夹
        respMap.put("host", host);
        respMap.put("expire", String.valueOf(expireEndTime / 1000));
        // respMap.put("expire", formatISO8601Date(expiration));


        return Resp.ok(respMap);
    }
}
