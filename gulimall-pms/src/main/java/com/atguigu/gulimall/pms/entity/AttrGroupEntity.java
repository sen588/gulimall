package com.atguigu.gulimall.pms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 属性分组
 * 
 * @author ÀîÉ­
 * @email 2050531502@qq.com
 * @date 2019-08-01 19:40:39
 */
@ApiModel
@Data
@TableName("pms_attr_group")
public class AttrGroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 分组id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(name = "attrGroupId",value = "分组id")
	private Long attrGroupId;
	/**
	 * 组名
	 */
	@ApiModelProperty(name = "attrGroupName",value = "组名")
	private String attrGroupName;
	/**
	 * 排序
	 */
	@ApiModelProperty(name = "sort",value = "排序")
	private Integer sort;
	/**
	 * 描述
	 */
	@ApiModelProperty(name = "descript",value = "描述")
	private String descript;
	/**
	 * 组图标
	 */
	@ApiModelProperty(name = "icon",value = "组图标")
	private String icon;
	/**
	 * 所属分类id
	 */
	@ApiModelProperty(name = "catelogId",value = "所属分类id")
	private Long catelogId;

}
