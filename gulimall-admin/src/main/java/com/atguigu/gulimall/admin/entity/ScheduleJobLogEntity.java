package com.atguigu.gulimall.wms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 定时任务日志
 * 
 * @author ÀîÉ­
 * @email 2050531502@qq.com
 * @date 2019-08-01 19:28:52
 */
@ApiModel
@Data
@TableName("schedule_job_log")
public class ScheduleJobLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 任务日志id
	 */
	@TableId
	@ApiModelProperty(name = "logId",value = "任务日志id")
	private Long logId;
	/**
	 * 任务id
	 */
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
	 * 任务状态    0：成功    1：失败
	 */
	@ApiModelProperty(name = "status",value = "任务状态    0：成功    1：失败")
	private Integer status;
	/**
	 * 失败信息
	 */
	@ApiModelProperty(name = "error",value = "失败信息")
	private String error;
	/**
	 * 耗时(单位：毫秒)
	 */
	@ApiModelProperty(name = "times",value = "耗时(单位：毫秒)")
	private Integer times;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(name = "createTime",value = "创建时间")
	private Date createTime;

}
