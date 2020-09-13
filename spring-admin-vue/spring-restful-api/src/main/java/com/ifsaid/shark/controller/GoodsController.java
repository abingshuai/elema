package com.ifsaid.shark.controller;

import com.github.pagehelper.PageInfo;
import com.ifsaid.shark.common.controller.BaseController;
import com.ifsaid.shark.entity.Goods;
import com.ifsaid.shark.entity.GoodsClassify;
import com.ifsaid.shark.service.IGoodsService;
import com.ifsaid.shark.util.JsonResult;
import com.ifsaid.shark.util.QueryParameter;
import com.ifsaid.shark.vo.GoodsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品管理
 * @author LINHAN
 */
@Api(tags = "商品管理")
@RestController
@RequestMapping("/dms/goods")
public class GoodsController extends BaseController<Goods,Integer,IGoodsService> {
    @Autowired
    private IGoodsService goodsService;

    /**
     * 获取商品分页信息
     * @param parameter
     * @return
     */
    @ApiOperation(value = "分页,获取商品信息", notes = "分页 查询所有，获取商品信息")
    @GetMapping("info/page")
    public JsonResult findAllInfoPage(QueryParameter parameter){
        PageInfo<Goods> page = goodsService.findAllPageInfo(parameter);
        return JsonResult.success(page.getTotal(), page.getList());
    }

    @ApiOperation(value = "新增商品", notes = "新增商品")
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

    @ApiOperation(value = "修改商品", notes = "修改商品")
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

    @ApiOperation(value = "删除商品", notes = "删除商品")
    @DeleteMapping("deleteGoods/{id}")
    public JsonResult<String> deleteGoods(@PathVariable("id") Integer id){
        try{
            int result = goodsService.deleteGoodsById(id);
            return JsonResult.success(result);
        }catch (Exception e){
            return JsonResult.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "获取所有商品分类", notes = "获取所有商品分类")
    @GetMapping("getAll")
    public JsonResult<List<GoodsClassify>> getAll(){
        try{
            List<GoodsClassify> typeList = goodsService.getAll();
            return JsonResult.success(typeList);
        }catch (Exception e){
            return JsonResult.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "获取所有商品分类及商品", notes = "获取所有商品分类及商品")
    @GetMapping("getAllByTypeId")
    public JsonResult<List<GoodsVo>> getAllByTypeId(){
        try{
            List<GoodsClassify> typeList = goodsService.getAll();
            List<GoodsVo> goodsVoList = new ArrayList<>();
            for (GoodsClassify goodsClassify:typeList) {
                GoodsVo goodsVo = new GoodsVo();
                goodsVo.setTypeId(goodsClassify.getId());
                goodsVo.setTypeName(goodsClassify.getName());
                goodsVo.setGoodsList(goodsService.getAllByTypeId(goodsClassify.getId()));
                goodsVoList.add(goodsVo);
            }
            return JsonResult.success(goodsVoList);
        }catch (Exception e){
            return JsonResult.fail(e.getMessage());
        }
    }
}
