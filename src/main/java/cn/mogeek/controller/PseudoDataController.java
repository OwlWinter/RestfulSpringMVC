package cn.mogeek.controller;

import cn.mogeek.model.Disciple;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PseudoDataController
 * @Description TODO
 * @Author owlwinter
 * @Date 2020/5/13 9:44
 * @Version 1.0
 **/

@Controller
@RequestMapping("/pseudodata")
public class PseudoDataController {
/*    @RequestMapping(value = "", method = RequestMethod.POST)
    public void addDisciple(@RequestBody Disciple disciple){
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
    }*/

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ModelAndView queryById(@PathVariable("id") Integer id){
        System.out.println("---------------");
        System.out.println("/search/{id}");
        System.out.println("@" + id);
        System.out.println("---------------");
        /*Disciple disciple = new Disciple();
        disciple.setId(id);*/

        /*Map<String, Object> stringObjectMap = new HashMap<>();
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
        }*/
        ModelAndView modelAndView = new ModelAndView("pseudo_data/disciple");
        modelAndView.addObject("id", id);
        return modelAndView;
    }

/*    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
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
    }*/



}
