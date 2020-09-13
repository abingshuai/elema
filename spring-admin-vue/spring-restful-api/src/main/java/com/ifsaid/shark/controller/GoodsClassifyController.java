package com.ifsaid.shark.controller;

import com.github.pagehelper.PageInfo;
import com.ifsaid.shark.common.controller.BaseController;
import com.ifsaid.shark.entity.Goods;
import com.ifsaid.shark.entity.GoodsClassify;
import com.ifsaid.shark.service.IGoodsClassifyService;
import com.ifsaid.shark.service.IGoodsService;
import com.ifsaid.shark.util.JsonResult;
import com.ifsaid.shark.util.QueryParameter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 *商品分类
 * @author LINHAN
 */
@Api(tags = "商品分类")
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
    @ApiOperation(value = "分页获取商品信息", notes = "分页获取商品信息")
    @GetMapping("info/page")
    public JsonResult findAllInfoPage(QueryParameter parameter){
        PageInfo<GoodsClassify> page = goodsClassifyService.findAllPageInfo(parameter);
        return JsonResult.success(page.getTotal(), page.getList());
    }

    @ApiOperation(value = "新增分类", notes = "新增分类")
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

    @ApiOperation(value = "修改分类", notes = "修改分类")
    @PutMapping("updateType")
    public JsonResult<GoodsClassify> updateType(@RequestBody GoodsClassify goodsClassify){
        try{
            int result = goodsClassifyService.updateType(goodsClassify);
            return JsonResult.success(result);
        }catch (Exception e){
            return JsonResult.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "删除分类", notes = "删除分类")
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
