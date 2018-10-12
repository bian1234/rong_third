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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 写操作 数据源
 */
@Configuration
@MapperScan(basePackages = "com.byk.rong.mapper.write", sqlSessionTemplateRef  = "thirdSqlSessionTemplate")
public class ThirdWriteDataSourceConfiguration {

    @Value("${spring.datasource.thirdWrite.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.thirdWrite.url}")
    private String url;

    @Value("${spring.datasource.thirdWrite.username}")
    private String username;

    @Value("${spring.datasource.thirdWrite.password}")
    private String password;

    @Bean(name = "thirdDataSource")
    @Primary
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        return dataSource;
    }

    @Bean(name = "thirdSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("thirdDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/write/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "thirdTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("thirdDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "thirdSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("thirdSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}