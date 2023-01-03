package org.example;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@MapperScan("org.example.rest.preference.entity")
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, MybatisAutoConfiguration.class })
public class PersistenceConfig
{


  // define an in-memory (non persistent) H2 datasource
  /*
  @Bean
  public DataSource getEmbeddedDataSource()
  {
    return new EmbeddedDatabaseBuilder()
      .setType(EmbeddedDatabaseType.H2)
      .addScript("schema.sql")
      .addScript("data.sql")
      .build();
  }
  */


  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  @Primary
  public DataSource getDataSource()
  {
    //DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
    return DataSourceBuilder.create().build();
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception
  {
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    //factoryBean.setDataSource(this.getDataSource());
    factoryBean.setDataSource(this.getDataSource());
    return factoryBean.getObject();
  }

}