package cn.mogeek.controller;

import cn.mogeek.model.Disciple;
import cn.mogeek.service.DiscipleService;
import org.apache.commons.el.GreaterThanOperator;
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

    /**
      * @Description: 数据分页查询，可自定义一页数据量
      * @Param: [page, page_size]
      * @return: java.util.Map<java.lang.String,java.lang.Object>
      * @Author: owlwinter
      * @Date: 2020/5/25
      */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getPage(int page, int page_size){
        int per_page;
        Map<String, Object> data = new HashMap<>();
        List<Disciple> buffer_disciple_list = null;

        try {
            buffer_disciple_list = service.query(new Disciple());
            per_page = (buffer_disciple_list.size() + page_size - 1)/page_size;
            List<Disciple> discipleList = null;
            if (buffer_disciple_list.size() == 0){
                /*分支处理：数据库为空*/
                data.put("list", null);
                data.put("count", 0);
                data.put("page", 0);
                data.put("per_page", 0);
                return RestResult.set(204,  messageSource.getMessage("Empty_list",
                        null, Locale.getDefault()), data);
            }else if (page <= 0 || page_size <= 0){
                /*分支处理：参数有误*/
                String field = page > 0 ? "page_size" : "page";
                data.put("count", buffer_disciple_list.size());
                data.put("page", page);
                data.put("per_page", per_page);
                data.put("list", null);
                return RestResult.set(400, messageSource.getMessage("page.Err_value",
                        new String[]{field, String.valueOf(page > 0 ? page_size : page)}, Locale.getDefault()), data);
            }else if ((page - 1)*page_size > buffer_disciple_list.size()){
                /*分支处理：请求数据超出数据库范围*/
                data.put("count", buffer_disciple_list.size());
                data.put("page", page);
                data.put("per_page", per_page);
                data.put("list", null);
                return RestResult.set(400, messageSource.getMessage("page.No_more",
                        null, Locale.getDefault()), data);

            }else {
                /*分支处理：返回请求数据*/
                if (page*page_size > buffer_disciple_list.size()){
                    discipleList = buffer_disciple_list.subList((page-1)*page_size, buffer_disciple_list.size());
                }else {
                    discipleList = buffer_disciple_list.subList((page-1)*page_size, page*page_size);
                }

                data.put("list", discipleList);
                data.put("count", buffer_disciple_list.size());
                data.put("page", page);
                data.put("per_page", per_page);
                return RestResult.set(200, messageSource.getMessage("page.Success",
                        null, Locale.getDefault()), data);

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return RestResult.set(500, messageSource.getMessage("page.Fail",
                null, Locale.getDefault()));
    }

    /**
      * @Description: POST 新增学员
      * @Param: [disciple]
      * @return: java.util.Map<java.lang.String,java.lang.Object>
      * @Author: owlwinter
      * @Date: 2020/5/25
      */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> addDisciple(@RequestBody Disciple disciple){

        if (disciple.getStudent_id() == null){
            /*分支处理：缺少字段*/
            return RestResult.set(400, messageSource.getMessage("Missing_field",
                    new String[]{"student_id"}, Locale.getDefault()));
        }else if (disciple.getStudent_name() == null){
            return RestResult.set(400, messageSource.getMessage("Missing_field",
                    new String[]{"student_name"}, Locale.getDefault()));
        }else {
            /*分支处理：新增数据、异常处理*/
            try {
                service.insert(disciple);
                Map<String, Object> data = new HashMap<>();
                data.put("disicple", disciple);
                return RestResult.set(201,
                        messageSource.getMessage("insert.Success",
                                null, Locale.getDefault()), data);
            }catch (DuplicateKeyException exception){
                return RestResult.set(400,
                        messageSource.getMessage("Student_id_repeatedly",
                                new String[]{disciple.getStudent_id().toString()}, Locale.getDefault()));
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return RestResult.set(500,
                messageSource.getMessage("insert.Fail", null, Locale.getDefault()));
    }

    /**
      * @Description: 根据 id 更新数据
      * @Param: [disciple]
      * @return: java.util.Map<java.lang.String,java.lang.Object>
      * @Author: owlwinter
      * @Date: 2020/5/25
      */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public @ResponseBody Map<String, Object> updateDisciple(@RequestBody Disciple disciple){

        if (disciple.getId() == null){
            return RestResult.set(400,
                    messageSource.getMessage("Missing_field", new String[]{"id"}, Locale.getDefault()));
        }else {
            try {
                boolean status = service.update(disciple);
                if (status){
                    return RestResult.set(201,
                            messageSource.getMessage("update.Success", null, Locale.getDefault()));
                }else {
                    return RestResult.set(204,
                            messageSource.getMessage("ID_does_not_exist",
                                    new String[]{"id", disciple.getId().toString()}, Locale.getDefault()));
                }
            }catch (DuplicateKeyException e){
                return RestResult.set(400,
                        messageSource.getMessage("Student_id_repeatedly",
                                new String[]{disciple.getStudent_id().toString()}, Locale.getDefault()));
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return RestResult.set(500, messageSource.getMessage("update.Fail", null, Locale.getDefault()));
    }

    /**
      * @Description: 根据 id 查询数据
      * @Param: [id]
      * @return: java.util.Map<java.lang.String,java.lang.Object>
      * @Author: owlwinter
      * @Date: 2020/5/25
      */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> queryById(@PathVariable("id") Integer id){
        System.out.println("---------------");
        System.out.println("/search/{id}");
        System.out.println("@" + id);
        System.out.println("---------------");
        Disciple disciple = new Disciple();
        disciple.setId(id);

        try {
            List<Disciple> discipleList = service.query(disciple);
            int count = discipleList.size();
            disciple = discipleList.get(0);
            if (count == 1){
                Map<String, Object> data = new HashMap<>();
                data.put("disciple", disciple);
                return RestResult.set(200,
                        messageSource.getMessage("query_by_id.Success", null, Locale.getDefault()), data);
            }else {
                return RestResult.set(204,
                        messageSource.getMessage("ID_does_not_exist",
                                new String[]{"id", id.toString()}, Locale.getDefault()));
            }
        }catch (IndexOutOfBoundsException e){
            return RestResult.set(204,
                    messageSource.getMessage("ID_does_not_exist",
                            new String[]{"id", id.toString()}, Locale.getDefault()));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return RestResult.set(500, messageSource.getMessage("query_by_id.Fail", null, Locale.getDefault()));
    }

    /**
      * @Description: 根据 id 删除数据
      * @Param: [id]
      * @return: java.util.Map<java.lang.String,java.lang.Object>
      * @Author: owlwinter
      * @Date: 2020/5/25
      */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Map<String, Object> deletedisciple(@PathVariable("id") Integer id){
        try {
            boolean status = service.delete(id);
            if (status){
                return RestResult.set(204,
                        messageSource.getMessage("delete.Success", null, Locale.getDefault()));
            }else {
                return RestResult.set(410,
                        messageSource.getMessage("ID_does_not_exist",
                                new String[]{"id", id.toString()}, Locale.getDefault()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestResult.set(500, messageSource.getMessage("delete.Fail", null, Locale.getDefault()));
    }

}
