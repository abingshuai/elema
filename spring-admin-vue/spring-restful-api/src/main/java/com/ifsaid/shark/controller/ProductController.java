package com.ifsaid.shark.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ifsaid.shark.common.domain.Query;
import com.ifsaid.shark.entity.Contact;
import com.ifsaid.shark.entity.Product;
import com.ifsaid.shark.service.IProductService;
import com.ifsaid.shark.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/dms/product")
public class ProductController {
    @Autowired
    IProductService service;
	@RequestMapping(value = "/search",method = RequestMethod.POST)
    public JsonResult commonSearch(@RequestBody Map<String,Object> params){
        Query query = new Query((Map<String, Object>) params.get("params"));
        return JsonResult.success(service.commonSearch(query));
    }
    @RequestMapping(value = "createProduct",method = RequestMethod.POST)
    public JsonResult createProduct(@RequestBody Map<String,Object> params){
        String jsonString = JSON.toJSONString(params.get("params"));
        Product product = JSONObject.parseObject(jsonString, Product.class);
        return JsonResult.success(service.createProduct(product));
    }
    @RequestMapping(value = "updateProduct",method = RequestMethod.POST)
    public JsonResult updateProduct(@RequestBody Map<String,Object> params){
        String jsonString = JSON.toJSONString(params.get("params"));
        Product product = JSONObject.parseObject(jsonString, Product.class);
        return JsonResult.success(service.update(product));
    }
    @RequestMapping(value = "deleteProduct",method = RequestMethod.GET)
    public JsonResult deleteProduct(@RequestParam String id){
        return JsonResult.success(service.deleteProduct(id));
    }

}
