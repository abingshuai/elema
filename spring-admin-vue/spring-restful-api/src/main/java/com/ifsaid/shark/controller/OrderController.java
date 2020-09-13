package com.ifsaid.shark.controller;

import com.github.pagehelper.PageInfo;
import com.ifsaid.shark.common.controller.BaseController;
import com.ifsaid.shark.entity.Goods;
import com.ifsaid.shark.entity.Order;
import com.ifsaid.shark.entity.OrderItem;
import com.ifsaid.shark.entity.SysUser;
import com.ifsaid.shark.service.IGoodsClassifyService;
import com.ifsaid.shark.service.IGoodsService;
import com.ifsaid.shark.service.IOrderService;
import com.ifsaid.shark.service.SysUserService;
import com.ifsaid.shark.util.JsonResult;
import com.ifsaid.shark.util.QueryParameter;
import com.ifsaid.shark.util.SnowFlakeUtils;
import com.ifsaid.shark.vo.OrderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单
 * @author LINHAN
 */
@Api(tags = "订单管理")
@RestController
@RequestMapping("/dms/order")
public class OrderController extends BaseController<Order,Integer, IOrderService> {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IGoodsClassifyService goodsClassifyService;

    /**
     * 获取订单分页信息
     * @param parameter
     * @return
     */
    @ApiOperation(value = "分页获取订单信息", notes = "分页获取订单信息")
    @GetMapping("info/page")
    public JsonResult findAllInfoPage(QueryParameter parameter){
        PageInfo<Order> page = orderService.findAllPageInfo(parameter);
        return JsonResult.success(page.getTotal(), page.getList());
    }

    @ApiOperation(value = "获取订单详情", notes = "获取订单详情")
    @GetMapping("getOrderItem/{orderNo}")
    public JsonResult<List<OrderItem>> getOrderItem(@PathVariable("orderNo") String orderNo){
        try {
            List<OrderItem> orderItem = orderService.getOrderItemByNo(orderNo);
            return JsonResult.success(orderItem);
        }catch (Exception e){
            return JsonResult.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "获取待消费订单", notes = "获取待消费订单")
    @GetMapping("getDxfOrder/{userId}")
    public JsonResult<List<Order>> getDxfOrder(@PathVariable("userId") String userId, List<OrderVo> orderVoList){
        Order order = new Order();
        String orderNo = SnowFlakeUtils.getId();
        order.setOrderNo(orderNo);
        order.setUserId(userId);
        order.setConcatName(sysUserService.getAllById(userId).getNickname());
        order.setConcat(sysUserService.getAllById(userId).getPhone());
        order.setStatus(1);

        BigDecimal price = null;
        Integer count = 0;

        for (OrderVo orderVo:orderVoList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderNo(orderNo);
            orderItem.setGoodsId(orderVo.getGoodsId());
            orderItem.setSubTotal(goodsService.findById(orderVo.getGoodsId()).getSellPrice().multiply(new BigDecimal(orderVo.getGoodsCount())));
            orderItem.setGoodsCount(orderVo.getGoodsCount());
            orderItem.setGoodsPrice(goodsService.findById(orderVo.getGoodsId()).getSellPrice());
            orderItem.setGoodsDescription(goodsService.findById(orderVo.getGoodsId()).getDescription());
            orderItem.setGoodsClassifyName(goodsClassifyService.findById(goodsService.findById(orderVo.getGoodsId()).getGoodsClassifyId()).getName());
            count+=orderVo.getGoodsCount();
            price = price.add(orderItem.getSubTotal());
            orderService.saveOrderItem(orderItem);
        }

        order.setTotalQuantity(count);
        order.setTotalPrice(price);
        orderService.create(order);

        try {
            List<Order> orderList = orderService.getOrderByStatus(userId,1);
            return JsonResult.success(orderList);
        }catch (Exception e){
            return JsonResult.fail(e.getMessage());
        }


    }
}
