package com.aop;

import org.springframework.stereotype.Component;

@Component("logschema")
public class LogSchema {
    public void before(){
        System.out.println("schema形式-前置通知");
    }
    public void afterreturning(){
        System.out.println("schema形式-后置通知");
    }
}
