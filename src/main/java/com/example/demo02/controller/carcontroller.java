package com.example.demo02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class carcontroller {
    @RequestMapping("/car/{x}/{y}")
   public String car(@PathVariable(value = "x") String x,@PathVariable(value = "y") String y){
        System.out.println("X:"+x+"Y:"+y);
        return "";
        
   }



}
