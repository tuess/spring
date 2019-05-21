package com.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;

//注解实现AOP
@Component("logAnnotation") //需要添加扫描
@Aspect
public class LogAnnotation {
    //还可以再直接注解一个@Pointcut，下面写一个空方法，后面的前置后置都可以直接调用这个
    @Pointcut("execution(public * com.service.StudentServiceImpl.addStudent(com.entity.Student))")
    public void mypoint(){}

    //前置通知
    @Before(value = "mypoint()") //属性：定义切点
    public void before(JoinPoint joinPoint){
        System.out.println("前置通知【注解形式】，目标对象："+joinPoint.getTarget()+"，方法名："+joinPoint.getSignature().getName()+"，参数列表："+ Arrays.toString(joinPoint.getArgs()));
    }

    //后置通知
    @AfterReturning(pointcut = "mypoint()",returning = "returningValue") //returning用于设置返回值
    public void afterreturning(JoinPoint joinPoint,Object returningValue){
        System.out.println("后置通知【注解形式】，目标对象："+joinPoint.getTarget()+"，方法名："+joinPoint.getSignature().getName()+"，参数列表："+ Arrays.toString(joinPoint.getArgs())+"，返回值："+returningValue);
    }

    //异常通知,如果要捕获特定的异常，需要在注解中加throwing参数,然后在方法中加入异常类型
    @AfterThrowing(value = "mypoint()",throwing = "e")
    public void exception(NullPointerException e){
        System.out.println("异常通知【注解形式】");
    }

    //环绕通知,参数是ProceedingJoinPoint
    @Around(value = "mypoint()")
    public void around(ProceedingJoinPoint joinPoint){
        //前置通知：方法执行之前
        System.out.println("环绕通知-前置通知-注解形式");
        try{
            //方法执行时
            joinPoint.proceed();
            //后置通知：方法执行之后
            System.out.println("环绕通知-后置通知-注解形式");
        } catch (Throwable e){
            //发生异常时：异常通知
            System.out.println("环绕通知-异常通知-注解形式");
        } finally {
            //最终通知
            System.out.println("环绕通知-最终通知-注解形式");
        }
    }

    //最终通知
    @After(value = "mypoint()")
    public void after(){
        System.out.println("最终通知【注解形式】");
    }
}