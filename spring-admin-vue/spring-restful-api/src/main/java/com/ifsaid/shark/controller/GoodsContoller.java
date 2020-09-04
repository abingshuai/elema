package com.ifsaid.shark.controller;

import com.github.pagehelper.PageInfo;
import com.ifsaid.shark.common.controller.BaseController;
import com.ifsaid.shark.entity.Goods;
import com.ifsaid.shark.entity.GoodsClassify;
import com.ifsaid.shark.service.IGoodsService;
import com.ifsaid.shark.util.JsonResult;
import com.ifsaid.shark.util.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品管理
 * @author LINHAN
 */
@RestController
@RequestMapping("/dms/goods")
public class GoodsContoller extends BaseController<Goods,Integer,IGoodsService> {
    @Autowired
    private IGoodsService goodsService;

    /**
     * 获取商品分页信息
     * @param parameter
     * @return
     */
    @RequestMapping("info/page")
    public JsonResult findAllInfoPage(QueryParameter parameter){
        PageInfo<Goods> page = goodsService.findAllPageInfo(parameter);
        return JsonResult.success(page.getTotal(), page.getList());
    }

    @PostMapping("saveGoods")
    public JsonResult<Goods> saveGoods(@RequestBody Goods goods){
        goods.setSellPrice((goods.getOriginPrice().multiply((goods.getDiscount().multiply(BigDecimal.valueOf(0.1))))));
        goods.setDeleted(0);
        try{
            int result = goodsService.saveGoods(goods);
            return JsonResult.success(result);
        }catch (Exception e){
            return JsonResult.fail(e.getMessage());
        }
    }

    @PutMapping("updateGoods")
    public JsonResult<Goods> updateGoods(@RequestBody Goods goods){
        goods.setSellPrice((goods.getOriginPrice().multiply((goods.getDiscount().multiply(BigDecimal.valueOf(0.1))))));
        try{
            int result = goodsService.updateGoods(goods);
            return JsonResult.success(result);
        }catch (Exception e){
            return JsonResult.fail(e.getMessage());
        }
    }

    @DeleteMapping("deleteGoods/{id}")
    public JsonResult<String> deleteGoods(@PathVariable("id") Integer id){
        try{
            int result = goodsService.deleteGoodsById(id);
            return JsonResult.success(result);
        }catch (Exception e){
            return JsonResult.fail(e.getMessage());
        }
    }

    @RequestMapping("getAll")
    public JsonResult<List<GoodsClassify>> getAll(){
        try{
            List<GoodsClassify> goodsList = goodsService.getAll();
            return JsonResult.success(goodsList);
        }catch (Exception e){
            return JsonResult.fail(e.getMessage());
        }
    }
}
