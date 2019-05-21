package com.dao;

import com.entity.Student;
import org.springframework.stereotype.Repository;

@Repository("studentDao") //相当于在xml文件中用bean标签注册了此类，需要添加扫描
public class StudentDaoImpl implements IStudentDao {

    @Override
    public void addStudent(Student student) {
        System.out.println("增加学生");
    }
}
