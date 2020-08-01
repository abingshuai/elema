package com.ifsaid.shark.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ifsaid.shark.common.domain.Query;
import com.ifsaid.shark.common.domain.SearchParmas;
import com.ifsaid.shark.common.domain.TableResultResponse;
import com.ifsaid.shark.entity.Contact;
import com.ifsaid.shark.entity.Dealer;
import com.ifsaid.shark.service.IContactService;
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
 * @since 2020-05-12
 */
@RestController
@RequestMapping("/dms/contact")
public class ContactController {
    @Autowired
    IContactService service;
	@RequestMapping(value = "search",method = RequestMethod.POST)
    public JsonResult<Contact> commonSearch(@RequestBody Map<String,Object> params){
        Query query = new Query((Map<String, Object>) params.get("params"));
        System.out.println(params+","+query);
        return JsonResult.success(service.commonSearch(query));
    }

    @RequestMapping(value = "highSearch",method = RequestMethod.POST)
    public JsonResult highSearch(@RequestBody Map<String,Object> params){
        List<SearchParmas> arry = (List<SearchParmas>) params.get("params");
        Map<String,Object> searchValues = SearchUtil.transformSearchParams(arry);
        Query query = new Query(searchValues);

        return JsonResult.success(service.highSearch(query));
    }

    @RequestMapping(value = "createContact",method = RequestMethod.POST)
    public JsonResult createContact(@RequestBody Map<String,Object> params){
        String jsonString = JSON.toJSONString(params.get("params"));
        Contact contact = JSONObject.parseObject(jsonString, Contact.class);


        return JsonResult.success(service.create(contact));
    }
    @RequestMapping(value = "updateContact",method = RequestMethod.POST)
    public JsonResult updateContact(@RequestBody Map<String,Object> params){
        String jsonString = JSON.toJSONString(params.get("params"));
        Contact contact = JSONObject.parseObject(jsonString, Contact.class);

        return JsonResult.success(service.update(contact));
    }

    @RequestMapping(value = "deleteContact",method = RequestMethod.GET)
    public JsonResult deleteContact(@RequestParam Integer id){

        return JsonResult.success(service.deleteById(id));
    }
//
//    @RequestMapping(value = "updateDealer",method = RequestMethod.POST)
//    public JsonResult updateDealer(@RequestBody Map<String,Object> params){
//        String jsonString = JSON.toJSONString(params.get("params"));
//        Dealer dealer = JSONObject.parseObject(jsonString, Dealer.class);
//        return JsonResult.success(service.update(dealer));
//    }
}
