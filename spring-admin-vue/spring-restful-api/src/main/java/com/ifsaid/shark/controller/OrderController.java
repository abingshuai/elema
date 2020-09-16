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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, paramType="path", dataType = "String")
    @PostMapping("getDxfOrder/{userId}")
    public JsonResult<List<Order>> getDxfOrder(@PathVariable("userId") String userId,@RequestBody List<OrderVo> orderVoList){
        Order order = new Order();
        String orderNo = SnowFlakeUtils.getId();
        order.setOrderNo(orderNo);
        order.setUserId(userId);
        order.setConcatName(sysUserService.getAllById(userId).getNickname());
        order.setConcat(sysUserService.getAllById(userId).getPhone());
        order.setDeleted(0);
        order.setStatus(1);

        BigDecimal price = new BigDecimal(0);
        Integer count = 0;

        for (OrderVo orderVo:orderVoList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderNo(orderNo);
            orderItem.setGoodsId(orderVo.getGoodsId());
            orderItem.setGoodsName(goodsService.findAllById(orderVo.getGoodsId()).getName());
            orderItem.setSubTotal(goodsService.findAllById(orderVo.getGoodsId()).getSellPrice().multiply(new BigDecimal(orderVo.getGoodsCount())));
            orderItem.setGoodsCount(orderVo.getGoodsCount());
            orderItem.setGoodsPrice(goodsService.findAllById(orderVo.getGoodsId()).getSellPrice());
            orderItem.setGoodsDescription(goodsService.findAllById(orderVo.getGoodsId()).getDescription());
            orderItem.setGoodsClassifyName(goodsService.findAllById(orderVo.getGoodsId()).getClassifyName());
            orderItem.setDeleted(0);
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

    @ApiOperation(value = "根据状态获取订单列表", notes = "根据状态获取订单列表")
    @GetMapping("getOrderByStatus/{userId}/{status}")
    public JsonResult<List<Order>> getOrderByStatus(@PathVariable("userId") String userId,@PathVariable("status") Integer status){
        try {
            List<Order> orderItem = orderService.getOrderByStatus(userId,status);
            return JsonResult.success(orderItem);
        }catch (Exception e){
            return JsonResult.fail(e.getMessage());
        }
    }


    @ApiOperation(value = "状态更新为已支付", notes = "状态更新为已支付")
    @PostMapping("updateYZFOrderStatus/{userId}/{orderNo}")
    public JsonResult<List<Order>> updateYZFOrderStatus(@PathVariable("userId") String userId,@PathVariable("orderNo") String orderNo){
        try {
            int orderStatus = orderService.updateOrderStatus(userId,orderNo,2);
            return JsonResult.success(orderStatus);
        }catch (Exception e){
            return JsonResult.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "状态更新为已完成", notes = "状态更新为已完成")
    @PostMapping("updateYWCOrderStatus/{userId}/{orderNo}")
    public JsonResult<List<Order>> updateYWCOrderStatus(@PathVariable("userId") String userId,@PathVariable("orderNo") String orderNo){
        try {
            int orderStatus = orderService.updateOrderStatus(userId,orderNo,3);
            List<OrderItem> orderItemList = orderService.getOrderItemByNo(orderNo);
            for(OrderItem orderItem: orderItemList){
                Goods goods = goodsService.findAllById(orderItem.getGoodsId());
               int totalSales = goodsService.findAllById(orderItem.getGoodsId()).getTotalSales() + orderItem.getGoodsCount();
               goods.setTotalSales(totalSales);
               goodsService.updateGoods(goods);
            }
            return JsonResult.success(orderStatus);
        }catch (Exception e){
            return JsonResult.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "状态更新为已退款", notes = "状态更新为已退款")
    @PostMapping("updateYTKOrderStatus/{userId}/{orderNo}")
    public JsonResult<List<Order>> updateYTKOrderStatus(@PathVariable("userId") String userId,@PathVariable("orderNo") String orderNo){
        try {
            int orderStatus = orderService.updateOrderStatus(userId,orderNo,4);
            return JsonResult.success(orderStatus);
        }catch (Exception e){
            return JsonResult.fail(e.getMessage());
        }
    }


}
