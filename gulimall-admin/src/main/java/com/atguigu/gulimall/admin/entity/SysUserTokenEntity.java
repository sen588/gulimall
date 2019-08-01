package com.atguigu.gulimall.wms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 系统用户Token
 * 
 * @author ÀîÉ­
 * @email 2050531502@qq.com
 * @date 2019-08-01 19:28:52
 */
@ApiModel
@Data
@TableName("sys_user_token")
public class SysUserTokenEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty(name = "userId",value = "")
	private Long userId;
	/**
	 * token
	 */
	@ApiModelProperty(name = "token",value = "token")
	private String token;
	/**
	 * 过期时间
	 */
	@ApiModelProperty(name = "expireTime",value = "过期时间")
	private Date expireTime;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(name = "updateTime",value = "更新时间")
	private Date updateTime;

}
