package com.system.util;

import com.alibaba.fastjson.JSONArray;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JsonData {
    public static String toString(List<Object> list, String msg) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("code", 0);
        map.put("msg", msg);
        map.put("count", list.size());
        map.put("data", list);
        return JSONArray.toJSONString(map);
    }
}

