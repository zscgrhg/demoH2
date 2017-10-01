package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@ServletComponentScan
public class DemoApplication {


    public static void main(String[] args) throws UnknownHostException {
        System.out.println("本机的IP = " + InetAddress.getLocalHost());
        SpringApplication.run(DemoApplication.class, args);
    }


}
