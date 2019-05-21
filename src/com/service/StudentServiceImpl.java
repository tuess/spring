package com.service;

import com.dao.IStudentDao;
import com.dao.StudentDaoImpl;
import com.entity.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("studentService") //需要添加扫描
public class StudentServiceImpl implements IStudnetService {
    IStudentDao iStudentDao = new StudentDaoImpl();

    public void setStudentDao(StudentDaoImpl studentDao) {
    }

    @Transactional() //事务的注解，readonly默认为false
    @Override
    public void addStudent(Student student) {
        //执行dao层中的addStudent方法
        //触发异常通知
//        iStudentDao=null;
        iStudentDao.addStudent(student);
    }

    public void deleteStudent(){
        System.out.println("删除学生");
    }
}
