package com.ifsaid.shark.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ifsaid.shark.common.domain.Query;
import com.ifsaid.shark.entity.Dealer;
import com.ifsaid.shark.entity.Stock;
import com.ifsaid.shark.service.IProductService;
import com.ifsaid.shark.service.IStockService;
import com.ifsaid.shark.service.IStoreHouseService;
import com.ifsaid.shark.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lzb
 * @since 2020-05-17
 */
@RestController
@RequestMapping("/dms/stock")
public class StockController {
    @Autowired
    IStockService service;


    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public JsonResult commonSearch(@RequestBody Map<String,Object> params){
        Query query = new Query((Map<String, Object>) params.get("params"));
        return JsonResult.success(service.commonSearch(query));
    }

    @RequestMapping(value = "/createStock",method = RequestMethod.POST)
    public JsonResult createStock(@RequestBody Map<String,Object> params){
        String jsonString = JSON.toJSONString(params.get("params"));
        Stock stock = JSONObject.parseObject(jsonString, Stock.class);
        stock.setStatus(1);
        return JsonResult.success((service.create(stock)));
    }

    @RequestMapping(value = "/updateStock",method = RequestMethod.POST)
    public JsonResult updateStock(@RequestBody Map<String,Object> params){
        String jsonString = JSON.toJSONString(params.get("params"));
        Stock stock = JSONObject.parseObject(jsonString, Stock.class);
        return JsonResult.success(service.update(stock));
    }

    @RequestMapping(value = "/handleInbound",method = RequestMethod.POST)
    public JsonResult handleInbound(@RequestBody Map<String,Object> params){
        String jsonString = JSON.toJSONString(params.get("params"));
        Stock stock = JSONObject.parseObject(jsonString, Stock.class);
        return JsonResult.success(service.handleInbound(stock));
    }

    @RequestMapping(value = "/handleOutbound",method = RequestMethod.POST)
    public JsonResult handleOutbound(@RequestBody Map<String,Object> params){
        String jsonString = JSON.toJSONString(params.get("params"));
        Stock stock = JSONObject.parseObject(jsonString, Stock.class);
        return JsonResult.success(service.handleOutbound(stock));
    }

}
