package com.mybatis.project.test;

import com.mybatis.project.bean.Student;
import com.mybatis.project.dao.StudentDAO;
import com.mybatis.project.mapper.StudentMapper;
import com.mybatis.project.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        //查询所有
//        List<Student> lists = StudentDAO.selectAll();
//        for (Student stu: lists){
//            System.out.println(stu);
//        }

        //分页查询
//        List<Student> lists = StudentDAO.selectPageByRB(1, 10);
//        for (Student stu: lists){
//            System.out.println(stu);
//        }


        //添加一则数据
        try {
            Student student = new Student();
            student.setName("Drive");
            student.setSex("男");
            student.setAge(21);
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse("1993-10-16");
            java.sql.Date birthday = new java.sql.Date(date.getTime());
            student.setBirthday(birthday);
            student.setSalary(99.99);
            System.out.println(StudentDAO.insertOne(student));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //添加多则数据 BindingException: Parameter 'name' not found. Available parameters are [collection, list]
//        try {
//            List<Student> lists = new ArrayList<>();
//            java.util.Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse("1991-06-18");
//            java.sql.Date birthday1 = new java.sql.Date(date1.getTime());
//            Student student1 = new Student("Den-O", "男", 27, birthday1, 89.99);
//            java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("1995-01-11");
//            java.sql.Date birthday2 = new java.sql.Date(date2.getTime());
//            Student student2 = new Student("Zi-O", "男", 20, birthday2, 66.66);
//            lists.add(student1);
//            lists.add(student2);
//            System.out.println(StudentDAO.insertSome(lists));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        //更新一则数据
//        Student student = StudentDAO.selectOne(10);
//        student.setAge(student.getAge() + 5);
//        student.setSalary(66.99);
//        System.out.println(StudentDAO.updateOne(student));

    }
}
