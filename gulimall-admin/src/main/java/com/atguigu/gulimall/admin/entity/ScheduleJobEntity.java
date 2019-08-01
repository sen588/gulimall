package com.atguigu.gulimall.wms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 定时任务
 * 
 * @author ÀîÉ­
 * @email 2050531502@qq.com
 * @date 2019-08-01 19:28:52
 */
@ApiModel
@Data
@TableName("schedule_job")
public class ScheduleJobEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 任务id
	 */
	@TableId
	@ApiModelProperty(name = "jobId",value = "任务id")
	private Long jobId;
	/**
	 * spring bean名称
	 */
	@ApiModelProperty(name = "beanName",value = "spring bean名称")
	private String beanName;
	/**
	 * 参数
	 */
	@ApiModelProperty(name = "params",value = "参数")
	private String params;
	/**
	 * cron表达式
	 */
	@ApiModelProperty(name = "cronExpression",value = "cron表达式")
	private String cronExpression;
	/**
	 * 任务状态  0：正常  1：暂停
	 */
	@ApiModelProperty(name = "status",value = "任务状态  0：正常  1：暂停")
	private Integer status;
	/**
	 * 备注
	 */
	@ApiModelProperty(name = "remark",value = "备注")
	private String remark;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(name = "createTime",value = "创建时间")
	private Date createTime;

}
