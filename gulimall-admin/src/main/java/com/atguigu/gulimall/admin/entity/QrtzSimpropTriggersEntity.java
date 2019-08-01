package com.atguigu.gulimall.wms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
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
@TableName("qrtz_simprop_triggers")
public class QrtzSimpropTriggersEntity implements Serializable {
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
	@ApiModelProperty(name = "strProp1",value = "")
	private String strProp1;
	/**
	 * 
	 */
	@ApiModelProperty(name = "strProp2",value = "")
	private String strProp2;
	/**
	 * 
	 */
	@ApiModelProperty(name = "strProp3",value = "")
	private String strProp3;
	/**
	 * 
	 */
	@ApiModelProperty(name = "intProp1",value = "")
	private Integer intProp1;
	/**
	 * 
	 */
	@ApiModelProperty(name = "intProp2",value = "")
	private Integer intProp2;
	/**
	 * 
	 */
	@ApiModelProperty(name = "longProp1",value = "")
	private Long longProp1;
	/**
	 * 
	 */
	@ApiModelProperty(name = "longProp2",value = "")
	private Long longProp2;
	/**
	 * 
	 */
	@ApiModelProperty(name = "decProp1",value = "")
	private BigDecimal decProp1;
	/**
	 * 
	 */
	@ApiModelProperty(name = "decProp2",value = "")
	private BigDecimal decProp2;
	/**
	 * 
	 */
	@ApiModelProperty(name = "boolProp1",value = "")
	private String boolProp1;
	/**
	 * 
	 */
	@ApiModelProperty(name = "boolProp2",value = "")
	private String boolProp2;

}
