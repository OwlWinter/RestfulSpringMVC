package cn.mogeek.controller;

import cn.mogeek.model.Disciple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RestResult
 * @Description TODO
 * @Author owlwinter
 * @Date 2020/5/25 14:43
 * @Version 1.0
 **/
public class RestResult {
/*    private Integer code;
    private String msg;
    private Disciple disciple;
    private List<Disciple> list;*/

    public static Map<String, Object> set(int code, String msg, Disciple disciple){
        Map<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("msg", msg);
        result.put("data", disciple);
        return result;
    }

    public static Map<String, Object> set(int code, String msg){
        Disciple data = null;
        return RestResult.set(code, msg, data);
    }

    public static Map<String, Object> set(int code, String msg, Map<String, Object> data){
        Map<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }
}
