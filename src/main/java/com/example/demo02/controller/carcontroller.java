package com.example.demo02.controller;

import com.example.demo02.util.RedisUtil;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Map;

@Controller

public class carcontroller {
    @Resource
    private RedisUtil redisUtil;
    @RequestMapping("/car/{x}/{y}")
   public String car( @PathVariable(value = "x") String x, @PathVariable(value = "y") String y){

        redisUtil.set("x",x);
        redisUtil.set("y",y);

        return "";
   }

   @RequestMapping("/getPosition")
    public String getPosition(){

       System.out.println(redisUtil.get("x")+" :"+redisUtil.get("y"));

       return "index1";
    }



}
