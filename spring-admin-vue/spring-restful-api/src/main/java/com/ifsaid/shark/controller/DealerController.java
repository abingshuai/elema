package com.ifsaid.shark.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ifsaid.shark.common.domain.Query;

import com.ifsaid.shark.common.domain.SearchParmas;
import com.ifsaid.shark.common.domain.TableResultResponse;
import com.ifsaid.shark.entity.Dealer;
import com.ifsaid.shark.service.IDealerService;
import com.ifsaid.shark.util.JsonResult;
import com.ifsaid.shark.util.SearchUtil;
import jdk.nashorn.internal.scripts.JS;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lzb
 * @since 2020-05-12
 */
@RestController
@RequestMapping("/dms/dealer")
public class DealerController {
    @Autowired
    IDealerService service;
    @RequestMapping("list")
    public JsonResult getList(){
        return JsonResult.success(service.findAll(null));
    }

    @RequestMapping("search")
    public JsonResult commonSearch(@RequestBody Map<String, Object> params){
        Query query = new Query((Map<String, Object>) params.get("params"));
        System.out.println(query);
//        Query query = new Query(params);
//        System.out.println(query+"哈哈");
        return JsonResult.success(service.commonSearch(query));
    }

    @RequestMapping(value = "highSearch",method = RequestMethod.POST)
    public JsonResult highSearch(@RequestBody Map<String,Object> params){
        List<SearchParmas> arry = (List<SearchParmas>) params.get("params");
        Map<String,Object> searchValues = SearchUtil.transformSearchParams(arry);
        Query query = new Query(searchValues);
        System.out.println(query);
        return JsonResult.success(service.highSearch(query));
    }



    @RequestMapping(value = "createDealer",method = RequestMethod.POST)
    public JsonResult createDealer(@RequestBody Map<String,Object> params){
        String jsonString = JSON.toJSONString(params.get("params"));
        Dealer dealer = JSONObject.parseObject(jsonString, Dealer.class);

        return JsonResult.success(service.create(dealer));
    }

    @RequestMapping(value = "updateDealer",method = RequestMethod.POST)
    public JsonResult updateDealer(@RequestBody Map<String,Object> params){
        String jsonString = JSON.toJSONString(params.get("params"));
        Dealer dealer = JSONObject.parseObject(jsonString, Dealer.class);
        return JsonResult.success(service.update(dealer));
    }



}
