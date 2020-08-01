package com.ifsaid.shark.service;

import com.ifsaid.shark.common.domain.Query;
import com.ifsaid.shark.common.domain.TableResultResponse;
import com.ifsaid.shark.common.service.BaseService;
import com.ifsaid.shark.entity.Contact;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lzb
 * @since 2020-05-12
 */
public interface IContactService extends BaseService<Contact,Integer> {
	TableResultResponse<Contact> commonSearch(Query query);
	TableResultResponse<Contact> highSearch(Query query);
}
