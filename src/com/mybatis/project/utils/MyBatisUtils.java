package com.mybatis.project.utils;

import com.mybatis.project.factory.MyDataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtils {

    private MyBatisUtils(){}

    /**
     * 从 XML 中构建 SqlSessionFactory: 每个基于 MyBatis 的应用都是以一个 SqlSessionFactory 的实例为核心的
     * SqlSessionFactory 的实例可以通过 SqlSessionFactoryBuilder 获得
     * SqlSessionFactoryBuilder 则可以从 XML 配置文件或一个预先配置的 Configuration 实例来构建出 SqlSessionFactory 实例
     * @return
     */
    public static SqlSessionFactory getSqlSessionFactory(){
        String resource = "mybatis-config.xml";
        SqlSessionFactory sessionFactory = null;
        try {
            InputStream is = Resources.getResourceAsStream(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    /**
     * 使用代码构建 SqlSessionFactory
     * @param clazz 映射器 如果同时存在映射器 .xml 文件与接口，且同名，则默认加载 .xml 文件
     * @return
     */
    public static SqlSessionFactory getSqlSessionFactory(Class<?> clazz){
        DataSource dataSource = MyDataSourceFactory.getMyDataSource();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        if (clazz != null) {
            configuration.addMapper(clazz);
        }
        return new SqlSessionFactoryBuilder().build
                (configuration);
    }

    /**
     * 获得 SqlSession 的实例
     * SqlSession 提供了在数据库执行 SQL 命令所需的所有方法
     * @return
     */
    public static SqlSession getSqlSession(){
        SqlSessionFactory factory = getSqlSessionFactory();
        return factory.openSession();
    }


}
