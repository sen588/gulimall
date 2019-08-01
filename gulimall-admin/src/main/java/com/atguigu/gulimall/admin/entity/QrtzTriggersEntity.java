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
@TableName("qrtz_triggers")
public class QrtzTriggersEntity implements Serializable {
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
	@ApiModelProperty(name = "description",value = "")
	private String description;
	/**
	 * 
	 */
	@ApiModelProperty(name = "nextFireTime",value = "")
	private Long nextFireTime;
	/**
	 * 
	 */
	@ApiModelProperty(name = "prevFireTime",value = "")
	private Long prevFireTime;
	/**
	 * 
	 */
	@ApiModelProperty(name = "priority",value = "")
	private Integer priority;
	/**
	 * 
	 */
	@ApiModelProperty(name = "triggerState",value = "")
	private String triggerState;
	/**
	 * 
	 */
	@ApiModelProperty(name = "triggerType",value = "")
	private String triggerType;
	/**
	 * 
	 */
	@ApiModelProperty(name = "startTime",value = "")
	private Long startTime;
	/**
	 * 
	 */
	@ApiModelProperty(name = "endTime",value = "")
	private Long endTime;
	/**
	 * 
	 */
	@ApiModelProperty(name = "calendarName",value = "")
	private String calendarName;
	/**
	 * 
	 */
	@ApiModelProperty(name = "misfireInstr",value = "")
	private Integer misfireInstr;
	/**
	 * 
	 */
	@ApiModelProperty(name = "jobData",value = "")
	private unknowType jobData;

}
