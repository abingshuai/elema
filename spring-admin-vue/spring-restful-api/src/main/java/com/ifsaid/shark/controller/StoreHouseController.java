package com.ifsaid.shark.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ifsaid.shark.common.domain.Query;
import com.ifsaid.shark.common.domain.SearchParmas;
import com.ifsaid.shark.entity.Product;
import com.ifsaid.shark.entity.StoreHouse;
import com.ifsaid.shark.service.IStockService;
import com.ifsaid.shark.service.IStoreHouseService;
import com.ifsaid.shark.util.JsonResult;
import com.ifsaid.shark.util.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
@RequestMapping("/dms/storeHouse")
public class StoreHouseController {
    @Autowired
    IStoreHouseService service;
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public JsonResult commonSearch(@RequestBody Map<String,Object> params){
        Query query = new Query((Map<String, Object>) params.get("params"));
        return JsonResult.success(service.commonSearch(query));
    }
    @RequestMapping(value = "updateStoreHouse",method = RequestMethod.POST)
    public JsonResult updateStoreHouse(@RequestBody Map<String,Object> params){
        String jsonString = JSON.toJSONString(params.get("params"));
        StoreHouse storeHouse = JSONObject.parseObject(jsonString, StoreHouse.class);
        return JsonResult.success(service.update(storeHouse));
    }
    @RequestMapping(value = "createStoreHouse",method = RequestMethod.POST)
    public JsonResult createStoreHouse(@RequestBody Map<String,Object> params){
        String jsonString = JSON.toJSONString(params.get("params"));
        StoreHouse storeHouse = JSONObject.parseObject(jsonString, StoreHouse.class);
        return JsonResult.success(service.create(storeHouse));
    }

    @RequestMapping(value = "deleteStoreHouse",method = RequestMethod.GET)
    public JsonResult deleteContact(@RequestParam Integer id){
        return JsonResult.success(service.deleteById(id));
    }

    @RequestMapping(value = "highSearch",method = RequestMethod.POST)
    public JsonResult highSearch(@RequestBody Map<String,Object> params){
        List<SearchParmas> arry = (List<SearchParmas>) params.get("params");
        Map<String,Object> searchValues = SearchUtil.transformSearchParams(arry);
        Query query = new Query(searchValues);
        System.out.println(query);
        return JsonResult.success(service.highSearch(query));
    }

    @RequestMapping(value = "getAvailableStoreHouse",method = RequestMethod.GET)
    public JsonResult getAvailableStoreHouse(@RequestParam Integer nums){
        return JsonResult.success(service.chooseAvailableStoreHouse(nums));
    }
}
