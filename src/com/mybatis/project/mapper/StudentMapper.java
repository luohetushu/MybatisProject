package com.mybatis.project.mapper;

import com.mybatis.project.bean.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 配置映射接口，使用注解的方式
 */
public interface StudentMapper {
    @Select("SELECT * FROM student WHERE id = #{id}")
    Student selectStudentById2(int id);

    /**
     * 字符串替换：${column}会被直接替换，#{value}会使用 ? 预处理
     * 注：用这种方式用作语句参数是不安全的，会导致潜在的 SQL 注入攻击。因此，要么不允许用户输入这些字段，要么自行转义并检验这些参数
     * @return
     */
    @Select("SELECT * FROM student WHERE ${column} = #{value}")
    Student selectOneByColumn(@Param("column") String column, @Param("value") String value);

}
