package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.font.FontRunIterator;

@RestController
public class HomeController {
    @RequestMapping("hello")
    public Hellowords hello(){
        String a=helloYa("yyy",3);
        System.out.println(a);
        return new Hellowords("orig");
    }
    public  String helloYa(String abc,int k){

        return abc;
    }
}
