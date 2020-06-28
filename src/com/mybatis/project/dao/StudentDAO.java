package com.mybatis.project.dao;

import com.mybatis.project.bean.Student;
import com.mybatis.project.mapper.StudentMapper;
import com.mybatis.project.utils.MyBatisUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务处理，只设计对数据库的处理（增、删、改、查），不涉及数据库的连接
 */
public class StudentDAO {

    /**
     * 查询所有
     * @return
     */
    public static List<Student> selectAll(){
        try(SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession()) {
            //通过配置映射器文件获取
            List<Student> lists = session.selectList("com.mybatis.project.mapper.StudentMapper.selectStudentAll");
            return lists;
        }
    }

    /**
     * mysql 数据库分页查询
     * SELECT * FROM 表名 LIMIT startIndex, pageSize;
     * @param startIndex  当前页下标
     * @param pageSize  当前页记录行数
     * @return
     */
    public static List<Student> selectPage(int startIndex, int pageSize){
        try(SqlSession session = MyBatisUtils.getSqlSession()) {
            startIndex = (startIndex <= 0) ? 1 : startIndex;
            pageSize = (pageSize <= 0) ? 10 : pageSize;
            Map<String, Integer> hashMap = new HashMap<>();
            hashMap.put("startIndex", (startIndex - 1) * pageSize);
            hashMap.put("pageSize", pageSize);
            List<Student> lists = session.selectList("com.mybatis.project.mapper.StudentMapper.selectStudentPage", hashMap);
            return lists;
        }
    }

    /**
     * 通过 RowBounds 进行查询结果分页显示
     * @param startIndex  当前页下标
     * @param pageSize 当前页记录行数
     * @return
     */
    public static List<Student> selectPageByRB(int startIndex, int pageSize){
        try(SqlSession session = MyBatisUtils.getSqlSession()) {
            startIndex = (startIndex <= 0) ? 1 : startIndex;
            pageSize = (pageSize <= 0) ? 10 : pageSize;
            RowBounds rowBounds = new RowBounds((startIndex - 1) * pageSize, pageSize);
            List<Student> lists = session.selectList("com.mybatis.project.mapper.StudentMapper.selectStudentAll", null, rowBounds);
            return lists;
        }
    }

    /**
     * 查询一则
     * @param id
     * @return
     */
    public static Student selectOne(int id){
        Student student = null;
        SqlSession session = null;
        try {
            session = MyBatisUtils.getSqlSessionFactory().openSession();
            //todo 通过配置映射器文件获取
            student = session
                    .selectOne("com.mybatis.project.mapper.StudentMapper.selectStudentById1", id);
            System.out.println(student);

            //todo 通过配置映射接口来实现
            StudentMapper mapper = session.getMapper(StudentMapper.class);
            Student student2 = mapper.selectStudentById2(10);
            System.out.println(student2);
            //todo 注：两种方式，映射器文件中 id 与 映射接口中方法不能同名
        } finally {
            if (session != null){
                session.close();
            }
        }
        return student;
    }

    /**
     * 插入一则
     * @param student
     * @return
     */
    public static int insertOne(Student student){
        try(SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession()) {
            int result = session.insert("com.mybatis.project.mapper.StudentMapper.addStudent", student);
            session.commit();
            return result;
        }
    }

    /**
     * 插入多则
     * @param lists
     * @return
     */
    public static int insertSome(List<Student> lists){
        try(SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession()) {
            int result = session.insert("com.mybatis.project.mapper.StudentMapper.addSomeStudent", lists);
            session.commit();
            return result;
        }
    }

    /**
     * 更新一则
     * @param student
     * @return
     */
    public static int updateOne(Student student){
        try(SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession()) {
            int result = session.update("com.mybatis.project.mapper.StudentMapper.updateStudent", student);
            session.commit();
            return result;
        }
    }

    /**
     * 删除一则
     * @param id
     * @return
     */
    public static int deleteOne(int id){
        try(SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession()) {
            int result = session.delete("com.mybatis.project.mapper.StudentMapper.updateStudent", id);
            session.commit();
            return result;
        }
    }

}
