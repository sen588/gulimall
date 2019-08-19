package com.atguigu.gulimall.pms.vo.detail;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetailSkuLadderVo {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    @ApiModelProperty(name = "id",value = "id")
    private Long id;
    /**
     * spu_id
     */
    @ApiModelProperty(name = "skuId",value = "spu_id")
    private Long skuId;
    /**
     * 满几件
     */
    @ApiModelProperty(name = "fullCount",value = "满几件")
    private Integer fullCount;
    /**
     * 打几折
     */
    @ApiModelProperty(name = "discount",value = "打几折")
    private BigDecimal discount;
    /**
     * 折后价
     */
    @ApiModelProperty(name = "price",value = "折后价")
    private BigDecimal price;
    /**
     * 是否叠加其他优惠[0-不可叠加，1-可叠加]
     */
    @ApiModelProperty(name = "addOther",value = "是否叠加其他优惠[0-不可叠加，1-可叠加]")
    private Integer addOther;
}
