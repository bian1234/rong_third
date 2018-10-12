package com.byk.rong.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 读操作数据源
 */
@Configuration
@MapperScan(basePackages = "com.byk.rong.mapper.read", sqlSessionTemplateRef  = "thirdReadSqlSessionTemplate")
public class ThirdReadDataSourceConfiguration {

    @Value("${spring.datasource.thirdRead.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.thirdRead.url}")
    private String url;

    @Value("${spring.datasource.thirdRead.username}")
    private String username;

    @Value("${spring.datasource.thirdRead.password}")
    private String password;


    @Bean(name = "thirdReadDataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        return dataSource;
    }

    @Bean(name = "thirdReadSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("thirdReadDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/read/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "thirdReadTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("thirdReadDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "thirdReadSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("thirdReadSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}