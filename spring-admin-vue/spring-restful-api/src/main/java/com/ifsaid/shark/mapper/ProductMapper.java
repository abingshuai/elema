package com.ifsaid.shark.mapper;


import com.ifsaid.shark.common.domain.Query;
import com.ifsaid.shark.common.mapper.BaseMapper;
import com.ifsaid.shark.entity.Product;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author lzb
 * @since 2020-05-17
 */
public interface ProductMapper extends BaseMapper<Product,Integer> {
    List<Product> commonSearch(Query query);
    Integer createProduct(Product product);
    Integer deleteProduct(String id);

}