package com.atguigu.gulimall.wms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 系统验证码
 * 
 * @author ÀîÉ­
 * @email 2050531502@qq.com
 * @date 2019-08-01 19:28:52
 */
@ApiModel
@Data
@TableName("sys_captcha")
public class SysCaptchaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * uuid
	 */
	@TableId
	@ApiModelProperty(name = "uuid",value = "uuid")
	private String uuid;
	/**
	 * 验证码
	 */
	@ApiModelProperty(name = "code",value = "验证码")
	private String code;
	/**
	 * 过期时间
	 */
	@ApiModelProperty(name = "expireTime",value = "过期时间")
	private Date expireTime;

}
