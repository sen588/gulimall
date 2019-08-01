package com.atguigu.gulimall.wms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 系统配置信息表
 * 
 * @author ÀîÉ­
 * @email 2050531502@qq.com
 * @date 2019-08-01 19:28:52
 */
@ApiModel
@Data
@TableName("sys_config")
public class SysConfigEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty(name = "id",value = "")
	private Long id;
	/**
	 * key
	 */
	@ApiModelProperty(name = "paramKey",value = "key")
	private String paramKey;
	/**
	 * value
	 */
	@ApiModelProperty(name = "paramValue",value = "value")
	private String paramValue;
	/**
	 * 状态   0：隐藏   1：显示
	 */
	@ApiModelProperty(name = "status",value = "状态   0：隐藏   1：显示")
	private Integer status;
	/**
	 * 备注
	 */
	@ApiModelProperty(name = "remark",value = "备注")
	private String remark;

}
