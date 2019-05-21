package com.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LogAround implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object result=null; //在方法开始处定义好返回值
        try{
            System.out.println("用环绕通知执行的【前置通知】");
            //控制目标方法的执行，result就是目标方法的返回值
            //在此方法执行之前的代码就是前置通知，后面的代码就是后置通知，catch里面的代码就是异常通知
            result = invocation.proceed();
            System.out.println("用环绕通知执行的【后置通知】");
            System.out.println("后置通知，目标对象："+invocation.getThis()+"，调用的方法名："+invocation.getMethod().getName()+"方法的参数个数："+invocation.getArguments().length+"，方法的返回值："+result);
        } catch (Exception e){
            System.out.println("用环绕通知执行的【异常通知】");
        }
        return result;
    }
}
