package cn.mogeek.controller;

import cn.mogeek.model.Disciple;
import cn.mogeek.service.DiscipleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName JsonDiscipleController
 * @Description TODO
 * @Author owlwinter
 * @Date 2020/5/12 15:18
 * @Version 1.0
 **/

@Controller
@RequestMapping("/disciple")
public class RestfulController {
    @Resource
    private DiscipleService service = null;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> addDisciple(@RequestBody Disciple disciple){
        Map<String, Object> response = new HashMap<>();
        try {
            service.insert(disciple);
            Integer id = disciple.getId();
            response.put("code", 201);
            response.put("data", id);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("code", 400);
        }
        return response;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public @ResponseBody Map<String, Object> updateDisciple(@RequestBody Disciple disciple){
        Map<String, Object> response = new HashMap<>();
        try {
            boolean status = service.update(disciple);
            int code = status ? 201 : 404;
            response.put("code", code);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("code", 500);
        }
        return response;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> queryById(@PathVariable("id") Integer id){
        System.out.println("---------------");
        System.out.println("/search/{id}");
        System.out.println("@" + id);
        System.out.println("---------------");
        Disciple disciple = new Disciple();
        disciple.setId(id);

        Map<String, Object> stringObjectMap = new HashMap<>();
        try {
            List<Disciple> discipleList = service.query(disciple);
            int count = discipleList.size();
            disciple = discipleList.get(0);
            int code = count == 1 ? 200 : 404;
            stringObjectMap.put("code", code);
            if (count == 1){
                stringObjectMap.put("disciple", disciple);
            }
        } catch (Exception e) {
            e.printStackTrace();
            stringObjectMap.put("code", 500);
        }
        return stringObjectMap;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> deletedisciple(@PathVariable("id") Integer id){
        Map<String, Object> stringObjectMap = new HashMap<>();
        try {
            boolean status = service.delete(id);
            int code = status ? 204 : 404;
            stringObjectMap.put("code", code);
        } catch (Exception e) {
            e.printStackTrace();
            stringObjectMap.put("code", 500);
        }
        return stringObjectMap;
    }



}
