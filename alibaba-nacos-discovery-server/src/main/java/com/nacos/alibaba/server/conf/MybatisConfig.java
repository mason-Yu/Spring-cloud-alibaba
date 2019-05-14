package com.nacos.alibaba.server.conf;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.google.common.collect.Lists;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages="com.nacos.alibaba.server.mapper",sqlSessionFactoryRef = "sqlSessionFactoryBean")
public class MybatisConfig {


    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource defaultDataSource() throws IOException {
        return  DruidDataSourceBuilder.create().build();
    }
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(defaultDataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        List<Resource> ress = Lists.newArrayList();
        ress.addAll(Lists.newArrayList(resolver.getResources("classpath:conf/mapping/*.xml")));
        for (Resource resource : ress) {
            System.out.println("res:"+resource.getFile().getPath());
        }
        sqlSessionFactoryBean.setMapperLocations(ress.toArray(new Resource[ress.size()]));
        return sqlSessionFactoryBean.getObject();
    }
}
