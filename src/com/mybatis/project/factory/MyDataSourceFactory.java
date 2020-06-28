package com.mybatis.project.factory;

import org.apache.ibatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

public class MyDataSourceFactory implements DataSourceFactory {

    private static DataSource dataSource;

    @Override
    public void setProperties(Properties properties) {

    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    public static DataSource getMyDataSource(){
        return dataSource;
    }

}
