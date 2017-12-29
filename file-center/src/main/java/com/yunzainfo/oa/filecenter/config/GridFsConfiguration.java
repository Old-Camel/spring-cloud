package com.yunzainfo.oa.filecenter.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * TODO:TODO
 * Auther:徐成
 * Date:2017/12/27
 * Email:old_camel@126.com
 */
@Configuration
@EnableMongoRepositories
// 读取配置文件的，通过Environment读取
//@PropertySource("classpath:mongo.yml")
public class GridFsConfiguration extends AbstractMongoConfiguration {

    //
    //@Autowired
    //Environment env;
@Value("${mongo.database}")
private String database;
@Value("${mongo.host}")
private String host;
@Value("${mongo.port}")
private Integer port;
    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
    }

    @Override
    protected String getDatabaseName() {
        return this.database;
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(this.host);
    }

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(this.host, this.port), getDatabaseName());
    }

}