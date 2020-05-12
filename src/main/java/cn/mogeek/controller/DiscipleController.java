package cn.mogeek.controller;

import cn.mogeek.model.Disciple;
import cn.mogeek.service.DiscipleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("web")
public class DiscipleController {
    @Resource
    private DiscipleService discipleService = null;

    /**
      * @Description: 查询所有数据展示到 /disciple/list 页面
      * @Param: []
      * @return: org.springframework.web.servlet.ModelAndView
      * @Author: owlwinter
      * @Date: 2020/5/11
      */
    @RequestMapping(value = "all")
    public ModelAndView all(){
        System.out.println("---------------");
        System.out.println("all");
        System.out.println("---------------");
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<Disciple> discipleList = discipleService.query(new Disciple());
            modelAndView.addObject("disciplelist", discipleList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.setViewName("web/list");
        return modelAndView;
    }

    /**
      * @Description: 打开查询页面
      * @Param: []
      * @return: org.springframework.web.servlet.ModelAndView
      * @Author: owlwinter
      * @Date: 2020/5/11
      */
    @RequestMapping("query")
    public ModelAndView query(){
        System.out.println("---------------");
        System.out.println("query");
        System.out.println("---------------");
        ModelAndView modelAndView = new ModelAndView();
        String action = "querydisciple";
        modelAndView.addObject("title", "查找数据");
        modelAndView.addObject("info", "填写学员信息");
        modelAndView.addObject("action", action);
        modelAndView.setViewName("web/query");
        return modelAndView;
    }

    /**
      * @Description: 查找指定的 disciple 对象并展示到 /diciple/list 页面
      * @Param: [disciple]
      * @return: org.springframework.web.servlet.ModelAndView
      * @Author: owlwinter
      * @Date: 2020/5/11
      */
    @RequestMapping("querydisciple")
    public ModelAndView querydisciple(Disciple disciple){
        System.out.println("---------------");
        System.out.println("querydisciple");
        System.out.println("@" + disciple);
        System.out.println("---------------");
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(disciple);
        try {
            List<Disciple> discipleList = discipleService.query(disciple);
            modelAndView.addObject("disciplelist", discipleList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.setViewName("web/list");
        return modelAndView;
    }


    /**
      * @Description: 打开新增数据页面
      * @Param: []
      * @return: org.springframework.web.servlet.ModelAndView
      * @Author: owlwinter
      * @Date: 2020/5/11
      */
    @RequestMapping("insert")
    public ModelAndView insert(){
        System.out.println("---------------");
        System.out.println("insert");
        System.out.println("---------------");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "插入数据");
        modelAndView.addObject("info", "填写学员信息");
        modelAndView.addObject("action", "adddisciple");
        modelAndView.setViewName("web/insert");
        return modelAndView;
    }

    /**
      * @Description: 永久化指定的 disciple 对象并返回到 /disciple/all
      * @Param: [disciple]
      * @return: org.springframework.web.servlet.ModelAndView
      * @Author: owlwinter
      * @Date: 2020/5/11
      */
    @RequestMapping(value = "adddisciple")

    public ModelAndView adddisciple(Disciple disciple){
        System.out.println("---------------");
        System.out.println("adddisciple");
        System.out.println("@" + disciple);
        System.out.println("---------------");
        ModelAndView modelAndView = new ModelAndView("redirect:all");
        System.out.println(disciple);
        try {
            disciple.setId(0);
            discipleService.insert(disciple);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
      * @Description: 获取指定 id 的 disciple 对象展示到 /disciple/update 页面供用户修改
      * @Param: [id]
      * @return: org.springframework.web.servlet.ModelAndView
      * @Author: owlwinter
      * @Date: 2020/5/11
      */
    @RequestMapping("update")
    public ModelAndView update(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        Disciple disciple = new Disciple();
        disciple.setId(id);
        try {
            disciple = discipleService.query(disciple).get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.addObject("title", "修改数据");
        modelAndView.addObject("info", "学员 id：" + id);
        modelAndView.addObject("action", "updatedisciple");
        modelAndView.addObject("old_disciple", disciple);
        System.out.println("---------------");
        System.out.println("update");
        System.out.println("@" + disciple);
        System.out.println("---------------");
        modelAndView.setViewName("web/update");
        return modelAndView;
    }

    /**
      * @Description: 永久化指定对象并跳转到 /disciple/all
      * @Param: [disciple]
      * @return: org.springframework.web.servlet.ModelAndView
      * @Author: owlwinter
      * @Date: 2020/5/11
      */
    @RequestMapping("updatedisciple")
    public ModelAndView updatedisciple(Disciple disciple){
        ModelAndView modelAndView = new ModelAndView("redirect:all");
        System.out.println("---------------");
        System.out.println("updatedisciple");
        System.out.println("@" + disciple);
        System.out.println("---------------");
        try {
            discipleService.update(disciple);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
      * @Description: 删除指定 id 的 disciple 对象并跳转到 /disciple/all
      * @Param: [id]
      * @return: java.lang.String
      * @Author: owlwinter
      * @Date: 2020/5/11
      */
    @RequestMapping("delete")
    public String delete(Integer id){
        System.out.println("---------------");
        System.out.println("delete");
        System.out.println("@" + id);
        System.out.println("---------------");
        try {
            discipleService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:all";
    }


}
