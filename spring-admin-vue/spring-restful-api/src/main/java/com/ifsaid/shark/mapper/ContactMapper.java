package com.ifsaid.shark.mapper;

import com.ifsaid.shark.common.domain.Query;
import com.ifsaid.shark.common.mapper.BaseMapper;
import com.ifsaid.shark.entity.Contact;
import com.ifsaid.shark.entity.Dealer;

import java.util.List;


/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author lzb
 * @since 2020-05-12
 */
public interface ContactMapper extends BaseMapper<Contact,Integer> {
      List<Contact> commonSearch(Query query);
      List<Contact> highSearch(Query query);
}