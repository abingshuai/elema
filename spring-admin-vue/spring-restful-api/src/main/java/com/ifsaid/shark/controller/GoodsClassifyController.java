package com.ifsaid.shark.controller;

import com.github.pagehelper.PageInfo;
import com.ifsaid.shark.common.controller.BaseController;
import com.ifsaid.shark.entity.Goods;
import com.ifsaid.shark.entity.GoodsClassify;
import com.ifsaid.shark.service.IGoodsClassifyService;
import com.ifsaid.shark.service.IGoodsService;
import com.ifsaid.shark.util.JsonResult;
import com.ifsaid.shark.util.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 *商品分类
 * @author LINHAN
 */
@RestController
@RequestMapping("/dms/type")
public class GoodsClassifyController extends BaseController<GoodsClassify,Integer, IGoodsClassifyService> {

    @Autowired
    private IGoodsClassifyService goodsClassifyService;

    /**
     * 获取分类分页信息
     * @param parameter
     * @return
     */
    @RequestMapping("info/page")
    public JsonResult findAllInfoPage(QueryParameter parameter){
        PageInfo<GoodsClassify> page = goodsClassifyService.findAllPageInfo(parameter);
        return JsonResult.success(page.getTotal(), page.getList());
    }

    @PostMapping("saveType")
    public JsonResult<GoodsClassify> saveType(@RequestBody GoodsClassify goodsClassify){
        goodsClassify.setDeleted(0);
        try{
            int result = baseService.create(goodsClassify);
            return JsonResult.success(result);
        }catch (Exception e){
            return JsonResult.fail(e.getMessage());
        }
    }

    @PutMapping("updateType")
    public JsonResult<GoodsClassify> updateType(@RequestBody GoodsClassify goodsClassify){
        try{
            int result = goodsClassifyService.updateType(goodsClassify);
            return JsonResult.success(result);
        }catch (Exception e){
            return JsonResult.fail(e.getMessage());
        }
    }

    @DeleteMapping("deleteType/{id}")
    public JsonResult<String> deleteType(@PathVariable("id") Integer id){
        try{
            int result = goodsClassifyService.deleteTypeById(id);
            return JsonResult.success(result);
        }catch (Exception e){
            return JsonResult.fail(e.getMessage());
        }
    }
}
