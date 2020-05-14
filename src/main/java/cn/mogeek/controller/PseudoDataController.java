package cn.mogeek.controller;

import cn.mogeek.model.Disciple;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
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

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ModelAndView toDelete(@PathVariable("id") Integer id){
        System.out.println("---------------");
        System.out.println("/pseudodata/{id}");
        System.out.println("@" + id);
        System.out.println("---------------");
        ModelAndView modelAndView = new ModelAndView("redirect:delete");
        return modelAndView;
    }

    @RequestMapping("/delete")
    public String delete(){
        System.out.println("---------------");
        System.out.println("/pseudodata/delete");
        System.out.println("---------------");
        return "pseudo_data/delete";
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView queryById(@PathVariable("id") Integer id){
        System.out.println("---------------");
        System.out.println("/pseudodata/{id}");
        System.out.println("@" + id);
        System.out.println("---------------");
        ModelAndView modelAndView = new ModelAndView("pseudo_data/disciple");
        modelAndView.addObject("id", id);
        return modelAndView;
    }


}
