package com.ifsaid.shark.controller;

import com.github.pagehelper.PageInfo;
import com.ifsaid.shark.common.controller.BaseController;
import com.ifsaid.shark.entity.Goods;
import com.ifsaid.shark.entity.Order;
import com.ifsaid.shark.entity.OrderItem;
import com.ifsaid.shark.service.IOrderService;
import com.ifsaid.shark.util.JsonResult;
import com.ifsaid.shark.util.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 订单
 * @author LINHAN
 */
@RestController
@RequestMapping("/dms/order")
public class OrderController extends BaseController<Order,Integer, IOrderService> {

    @Autowired
    private IOrderService orderService;

    /**
     * 获取订单分页信息
     * @param parameter
     * @return
     */
    @RequestMapping("info/page")
    public JsonResult findAllInfoPage(QueryParameter parameter){
        PageInfo<Order> page = orderService.findAllPageInfo(parameter);
        return JsonResult.success(page.getTotal(), page.getList());
    }

    @RequestMapping("getOrderItem/{orderNo}")
    public JsonResult<List<OrderItem>> getOrderItem(@PathVariable("orderNo") String orderNo){
        try {
            List<OrderItem> orderItem = orderService.getOrderItemByNo(orderNo);
            return JsonResult.success(orderItem);
        }catch (Exception e){
            return JsonResult.fail(e.getMessage());
        }
    }
}
