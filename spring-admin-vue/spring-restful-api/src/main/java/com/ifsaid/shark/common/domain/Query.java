package com.ifsaid.shark.common.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Query extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	//当前页码
    private int page = 1;
    //每页条数
    private int limit = 10;

    private String orderBy;
    
    private long count; //总记录数

    public Query(Map<String, Object> params){
        this.putAll(params);
        //分页参数
        if(params.get("page")!=null) {
            this.page = Integer.parseInt(params.get("page").toString());
        }
        if(params.get("limit")!=null) {
            this.limit = Integer.parseInt(params.get("limit").toString());
        }
        if(params.get("orderBy") != null && !"".equals(params.get("orderBy").toString().trim()) && !"null".equals(params.get("orderBy").toString().trim())){
            this.orderBy = params.get("orderBy").toString().trim();
        }
        this.remove("page");
        this.remove("limit");
        this.remove("orderBy");
        this.remove("_t");
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
    
    public long getCount() {
		return count;
	}


	public void setCount(long count) {
		this.count = count;
	}


	/**
	 * 获取 Hibernate FirstResult
	 */
	public int getFirstResult(){
		int firstResult = (getPage() - 1) * getLimit();
		if (firstResult >= getCount() || firstResult<0) {
			firstResult = 0;
		}
		return firstResult;
	}
	/**
	 * 获取 Hibernate MaxResults
	 */
	public int getMaxResults(){
		return getLimit();
	}
}