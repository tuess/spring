package com.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("course")
public class Course {
    @Value("ruby")
    private String courseName;
    @Value("1999")
    private int courseHour;
    private Teacher teacher; //授课老师，依赖于Teacher类

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseHour() {
        return courseHour;
    }

    public void setCourseHour(int courseHour) {
        this.courseHour = courseHour;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void showInfo(){
        System.out.println(this.courseName+","+this.courseHour+","+this.teacher.getName()+","+this.teacher.getAge());
    }

    public Course(String courseName, int courseHour, Teacher teacher) {
        this.courseName = courseName;
        this.courseHour = courseHour;
        this.teacher = teacher;
    }

    public Course() {
    }
}
