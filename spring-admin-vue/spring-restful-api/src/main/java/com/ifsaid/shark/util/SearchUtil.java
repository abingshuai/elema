package com.ifsaid.shark.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ifsaid.shark.common.domain.SearchParmas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchUtil {
    public static Map<String,Object> transformSearchParams(List<SearchParmas> list){
        Map<String,Object> searchValues = new HashMap<>();

        for(Object sp:list){
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(sp));
            SearchParmas temp = jsonObject.toJavaObject(SearchParmas.class);
            System.out.println(temp);
            searchValues.put(temp.getId(),temp.getValue());
        }
        return searchValues;
    }
}
