package com.atguigu.gulimall.wms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author ÀîÉ­
 * @email 2050531502@qq.com
 * @date 2019-08-01 19:28:52
 */
@ApiModel
@Data
@TableName("qrtz_simple_triggers")
public class QrtzSimpleTriggersEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty(name = "schedName",value = "")
	private String schedName;
	/**
	 * 
	 */
	@ApiModelProperty(name = "triggerName",value = "")
	private String triggerName;
	/**
	 * 
	 */
	@ApiModelProperty(name = "triggerGroup",value = "")
	private String triggerGroup;
	/**
	 * 
	 */
	@ApiModelProperty(name = "repeatCount",value = "")
	private Long repeatCount;
	/**
	 * 
	 */
	@ApiModelProperty(name = "repeatInterval",value = "")
	private Long repeatInterval;
	/**
	 * 
	 */
	@ApiModelProperty(name = "timesTriggered",value = "")
	private Long timesTriggered;

}
