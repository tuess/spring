package com.entity;

import com.course.Course;
import com.course.HtmlCourse;
import com.course.JavaCourse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Student {
    private int id;
    private String name;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return this.id+","+this.name+","+this.age;
    }

    public void learnJava(){
        JavaCourse javaCourse = new JavaCourse();
        javaCourse.learn();
    }

    public void learnHtml(){
        HtmlCourse htmlCourse = new HtmlCourse();
        htmlCourse.learn();
    }

    //学习任何课程
    public void learn(String name){
//        从自己写的简单工厂中获取课程
//        Course course = CourseFactory.getCourse(name);

//        从ioc提供的超级工厂获取课程（之前设置过bean）
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Course course = (Course) context.getBean(name);
        course.learn();
    }
}
