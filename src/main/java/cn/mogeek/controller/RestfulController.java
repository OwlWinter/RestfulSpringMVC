package cn.mogeek.controller;

import cn.mogeek.model.Disciple;
import cn.mogeek.service.DiscipleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.awt.windows.WBufferStrategy;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RestfulController
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

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getPage(int page, int per_page){
        int page_size = 10;
        Map<String, Object> stringObjectMap = new HashMap<>();
        List<Disciple> buffer_disciple_list = null;
        try {
            buffer_disciple_list = service.query(new Disciple());

            per_page = (buffer_disciple_list.size() + page_size - 1)/page_size;
            List<Disciple> discipleList = null;
            if (page*page_size > buffer_disciple_list.size()){
                discipleList = buffer_disciple_list.subList((page-1)*page_size, buffer_disciple_list.size());
            }else {
                discipleList = buffer_disciple_list.subList((page-1)*page_size, page*page_size);
            }

            stringObjectMap.put("code", 200);
            stringObjectMap.put("data", discipleList);
            stringObjectMap.put("count", buffer_disciple_list.size());
            stringObjectMap.put("page", page);
            stringObjectMap.put("per_page", per_page);
        } catch (Exception e) {
            stringObjectMap.put("code", 500);
            e.printStackTrace();
        }


        return stringObjectMap;
    }

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
    public @ResponseBody Map<String, Object> deletedisciple(@PathVariable("id") Integer id){
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
