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
@TableName("qrtz_job_details")
public class QrtzJobDetailsEntity implements Serializable {
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
	@ApiModelProperty(name = "jobClassName",value = "")
	private String jobClassName;
	/**
	 * 
	 */
	@ApiModelProperty(name = "isDurable",value = "")
	private String isDurable;
	/**
	 * 
	 */
	@ApiModelProperty(name = "isNonconcurrent",value = "")
	private String isNonconcurrent;
	/**
	 * 
	 */
	@ApiModelProperty(name = "isUpdateData",value = "")
	private String isUpdateData;
	/**
	 * 
	 */
	@ApiModelProperty(name = "requestsRecovery",value = "")
	private String requestsRecovery;
	/**
	 * 
	 */
	@ApiModelProperty(name = "jobData",value = "")
	private unknowType jobData;

}
