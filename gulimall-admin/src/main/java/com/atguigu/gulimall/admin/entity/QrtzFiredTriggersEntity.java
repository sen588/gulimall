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
@TableName("qrtz_fired_triggers")
public class QrtzFiredTriggersEntity implements Serializable {
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
	@ApiModelProperty(name = "entryId",value = "")
	private String entryId;
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
	@ApiModelProperty(name = "instanceName",value = "")
	private String instanceName;
	/**
	 * 
	 */
	@ApiModelProperty(name = "firedTime",value = "")
	private Long firedTime;
	/**
	 * 
	 */
	@ApiModelProperty(name = "schedTime",value = "")
	private Long schedTime;
	/**
	 * 
	 */
	@ApiModelProperty(name = "priority",value = "")
	private Integer priority;
	/**
	 * 
	 */
	@ApiModelProperty(name = "state",value = "")
	private String state;
	/**
	 * 
	 */
	@ApiModelProperty(name = "jobName",value = "")
	private String jobName;
	/**
	 * 
	 */
	@ApiModelProperty(name = "jobGroup",value = "")
	private String jobGroup;
	/**
	 * 
	 */
	@ApiModelProperty(name = "isNonconcurrent",value = "")
	private String isNonconcurrent;
	/**
	 * 
	 */
	@ApiModelProperty(name = "requestsRecovery",value = "")
	private String requestsRecovery;

}
