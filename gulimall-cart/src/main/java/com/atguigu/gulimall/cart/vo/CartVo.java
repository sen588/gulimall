package com.atguigu.gulimall.cart.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 整个购物车
 */

public class CartVo {

    private Integer totalCount; //总商品数量

    private BigDecimal totalPrice;//总商品价格

    private BigDecimal reductionPrice;//优惠了的价格

    private BigDecimal cartPrice;//购物车应该支付的价格

    @Getter
    @Setter
    private List<CartItemVo> items;//购物车中所有的购物项；

    @Getter
    @Setter
    private String userKey;//临时用户的key

    /**
     * 总数量
     * @return
     */
    public Integer getTotalCount()
    {
        Integer num = 0;
        if(items!=null && items.size()>0)
        {
            for (CartItemVo item : items)
            {
                num += item.getNum();
            }
        }
        return num;
    }

    /**
     * 商品总价格
     * @return
     */
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = getTotalPrice();
        if(items!=null && items.size()>0)
        {
            for (CartItemVo item : items)
            {
                this.totalPrice = this.totalPrice.add(item.getPrice());
            }
        }
        return totalPrice;
    }


    /**
     * 满减信息
     * @return
     */
    public BigDecimal getReductionPrice() {
        BigDecimal reduce = new BigDecimal("0.0");
        if(items!=null && items.size()>0)
        {
            for (CartItemVo item : items)
            {
                List<SkuFullReductionVo> reductions = item.getReductions();
                LinkedBlockingDeque<SkuFullReductionVo> fullReductionVos = new LinkedBlockingDeque<>();

                for (SkuFullReductionVo reduction : reductions) {
                    if(reduction.getAddOther() == 1){
                        //代表可以叠加优惠
                        fullReductionVos.addFirst(reduction);
                    }else {
                        fullReductionVos.addLast(reduction);
                    }
                }
                //计算满减打折等可以减掉的金额
                if(reductions!=null && reductions.size()>0)
                {
                    reduce = getDiscountMessage(reduce, item, reductions);
                }
                List<SkuCouponVo> coupons = item.getCoupons();
                if(coupons!=null && coupons.size()>0)
                {
                    for (SkuCouponVo coupon : coupons) {
                        BigDecimal amount = coupon.getAmount();
                        reduce = reduce.add(amount);
                    }
                }
            }
        }
        return reductionPrice;
    }

    /**
     * 减后的价格
     * @return
     */
    public BigDecimal getCartPrice() {
        //优惠的价格
        BigDecimal reductionPrice = getReductionPrice();
        //总价格
        BigDecimal totalPrice = getTotalPrice();
        return totalPrice.subtract(reductionPrice);
    }

    /**
     * 计算满减打折等可以减掉的金额
     * @param reduce
     * @param item
     * @param reductions
     * @return
     */
    private BigDecimal getDiscountMessage(BigDecimal reduce, CartItemVo item, List<SkuFullReductionVo> reductions)
    {
        for (SkuFullReductionVo reduction : reductions)
        {
            Integer type = reduction.getType();
            Integer addOther = reduction.getAddOther();
            // 0-打折 1-满减
            if(type == 0)
            {
                //满减打几折
                Integer fullCount = reduction.getFullCount();
                Integer discount = reduction.getDiscount();
                if(item.getNum() >= fullCount)
                {
                    BigDecimal multiply = item.getTotalPrice().multiply(new BigDecimal((discount/100) + "0." + (discount%100)));
                    BigDecimal subtract = item.getTotalPrice().subtract(multiply);
                    reduce = reduce.add(subtract);
                }
            }
            if(type == 1)
            {
                //满多少钱减多少钱
                BigDecimal fullPrice = reduction.getFullPrice();
                BigDecimal reducePrice = reduction.getReducePrice();
                if(item.getTotalPrice().subtract(fullPrice).compareTo(new BigDecimal("0.0")) > -1)
                {
                    //累加优惠券
                    reduce = reduce.add(reducePrice);
                }
            }
            if (addOther == 0)
            {
                break;
            }
        }
        return reduce;
    }
}
