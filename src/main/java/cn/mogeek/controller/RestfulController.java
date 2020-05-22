package cn.mogeek.controller;

import cn.mogeek.model.Disciple;
import cn.mogeek.service.DiscipleService;
//import com.sun.org.apache.xpath.internal.operations.String;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.awt.windows.WBufferStrategy;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
    @Autowired
    private DiscipleService service;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getPage(int page, int per_page){
        int page_size = 10;
        Map<String, Object> response = new HashMap<>();
        List<Disciple> buffer_disciple_list = null;
        try {
            buffer_disciple_list = service.query(new Disciple());
            per_page = (buffer_disciple_list.size() + page_size - 1)/page_size;
            List<Disciple> discipleList = null;

            if (buffer_disciple_list.size() == 0){
                response.put("code", 204);
                response.put("msg", messageSource.getMessage("Empty_list",
                        null, Locale.getDefault()));
            }else if ((page - 1)*page_size > buffer_disciple_list.size() || page <= 0){
                response.put("code", 400);
                response.put("count", buffer_disciple_list.size());
                response.put("page", page);
                response.put("per_page", per_page);
                response.put("msg", messageSource.getMessage("page.Out_of_index",
                        null, Locale.getDefault()));
            } else {
                if (page*page_size > buffer_disciple_list.size()){
                    discipleList = buffer_disciple_list.subList((page-1)*page_size, buffer_disciple_list.size());
                }else {
                    discipleList = buffer_disciple_list.subList((page-1)*page_size, page*page_size);
                }

                response.put("code", 200);
                response.put("data", discipleList);
                response.put("count", buffer_disciple_list.size());
                response.put("page", page);
                response.put("per_page", per_page);
                response.put("msg", messageSource.getMessage("page.Success",
                        null, Locale.getDefault()));
            }
        } catch (Exception e) {
            response.put("code", 500);
            response.put("msg", messageSource.getMessage("page.Fail",
                    null, Locale.getDefault()));
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> addDisciple(@RequestBody Disciple disciple){
        System.out.println("\n=========\n" + disciple.getStudent_id() + ":" + disciple.getStudent_name());
        Map<String, Object> response = new HashMap<>();

        if (disciple.getStudent_id() == null){
            response.put("code", 400);
            response.put("msg", messageSource.getMessage("Missing_field",
                    new String[]{"student_id"}, Locale.getDefault()));
        } else if (disciple.getStudent_name() == null){
            response.put("code", 400);
            response.put("msg", messageSource.getMessage("Missing_field",
                    new String[]{"student_name"}, Locale.getDefault()));
        } else {
            try {
                service.insert(disciple);
                Integer id = disciple.getId();
                response.put("code", 201);
                response.put("data", disciple);
                response.put("msg", messageSource.getMessage("insert.Success",
                        null, Locale.getDefault()));
            }catch (DuplicateKeyException e){
                response.put("code", 400);
                response.put("msg", messageSource.getMessage("Student_id_repeatedly",
                        new String[]{"student_id", disciple.getStudent_id().toString()}, Locale.getDefault()));
            }catch (Exception e) {
                e.printStackTrace();
                response.put("code", 500);
                response.put("msg", messageSource.getMessage("insert.Fail",
                        null, Locale.getDefault()));
            }
        }

        return response;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public @ResponseBody Map<String, Object> updateDisciple(@RequestBody Disciple disciple){
        Map<String, Object> response = new HashMap<>();

        if (disciple.getId() == null){
            response.put("code", 400);
            response.put("msg", messageSource.getMessage("Missing_field",
                    new String[]{"id"}, Locale.getDefault()));
        }else {
            try {
                boolean status = service.update(disciple);
                if (status){
                    response.put("code", 201);
                    response.put("msg", messageSource.getMessage("update.Success",
                            null, Locale.getDefault()));
                }else {
                    response.put("code", 204);
                    response.put("msg", messageSource.getMessage("ID_does_not_exist",
                            new String[]{"id", disciple.getId().toString()}, Locale.getDefault()));
                }
            }catch (DuplicateKeyException e){
                response.put("code", 400);
                response.put("msg", messageSource.getMessage("Student_id_repeatedly",
                        new String[]{"student_id", disciple.getStudent_id().toString()}, Locale.getDefault()));
            }catch (Exception e) {
                e.printStackTrace();
                response.put("code", 500);
                response.put("msg", messageSource.getMessage("update.Fail",
                        null, Locale.getDefault()));
            }
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
        Map<String, Object> response = new HashMap<>();

        try {
            List<Disciple> discipleList = service.query(disciple);
            int count = discipleList.size();
            disciple = discipleList.get(0);
            if (count == 1){
                response.put("code", 200);
                response.put("disciple", disciple);
                response.put("msg", messageSource.getMessage("query_by_id.Success",
                        null, Locale.getDefault()));
            }else {
                response.put("code", 204);
                response.put("msg", messageSource.getMessage("ID_does_not_exist",
                        new String[]{"id", id.toString()}, Locale.getDefault()));
            }
        }catch (IndexOutOfBoundsException e){
            response.put("code", 204);
            response.put("msg", messageSource.getMessage("ID_does_not_exist",
                    new String[]{"id", id.toString()}, Locale.getDefault()));
        }catch (Exception e) {
            e.printStackTrace();
            response.put("code", 500);
            response.put("msg", messageSource.getMessage("query_by_id.Fail",
                    null, Locale.getDefault()));
        }
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Map<String, Object> deletedisciple(@PathVariable("id") Integer id){
        Map<String, Object> response = new HashMap<>();
        try {
            boolean status = service.delete(id);
            if (status){
                response.put("code", 204);
                response.put("msg", messageSource.getMessage("delete.Success",
                        null, Locale.getDefault()));
            }else {
                response.put("code", 410);
                response.put("msg", messageSource.getMessage("ID_does_not_exist",
                        new String[]{"id", id.toString()}, Locale.getDefault()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("code", 500);
            response.put("msg", messageSource.getMessage("delete.Fail",
                    null, Locale.getDefault()));
        }
        return response;
    }

}
