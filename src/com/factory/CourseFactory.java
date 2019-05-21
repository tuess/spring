package com.factory;

import com.course.Course;
import com.course.HtmlCourse;
import com.course.JavaCourse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CourseFactory {
    public static Course getCourse(String name){
        //获取ioc容器
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        if(name.equals("Java")){
            return (Course) context.getBean("JavaCourse");
        } else {
            return (Course) context.getBean("HtmlCourse");
        }
    }
}