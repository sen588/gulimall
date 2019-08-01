package com.atguigu.gulimall.wms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户
 * 
 * @author ÀîÉ­
 * @email 2050531502@qq.com
 * @date 2019-08-01 19:28:52
 */
@ApiModel
@Data
@TableName("tb_user")
public class TbUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty(name = "userId",value = "")
	private Long userId;
	/**
	 * 用户名
	 */
	@ApiModelProperty(name = "username",value = "用户名")
	private String username;
	/**
	 * 手机号
	 */
	@ApiModelProperty(name = "mobile",value = "手机号")
	private String mobile;
	/**
	 * 密码
	 */
	@ApiModelProperty(name = "password",value = "密码")
	private String password;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(name = "createTime",value = "创建时间")
	private Date createTime;

}
