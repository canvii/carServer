package com.example.demo02.controller;

import com.example.demo02.util.RedisUtil;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller

public class carcontroller {
    @Resource
    private RedisUtil redisUtil;
    @RequestMapping("/car/{x}/{y}")
   public String car( @PathVariable(value = "x") String x, @PathVariable(value = "y") String y){

        redisUtil.set("x",x);
        redisUtil.set("y",y);
        System.out.println(x+" : "+y);
        return "";
   }



    @RequestMapping("/getPosition")
    public void getPosition(HttpServletRequest request , HttpServletResponse response){

//        System.out.println(redisUtil.get("x")+" :"+redisUtil.get("y"));

       String s = "x"+redisUtil.get("x")+"y"+redisUtil.get("y");
        try {
            response.getWriter().println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
