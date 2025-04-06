package org.andrey.hadooptest.config;

import org.apache.hadoop.fs.FileSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;

@Configuration
public class HadoopConfig {
    @Bean
    public org.apache.hadoop.conf.Configuration hadoopConfiguration() {
        org.apache.hadoop.conf.Configuration configuration = new org.apache.hadoop.conf.Configuration();
        configuration.set("fs.defaultFS", "hdfs://namenode:8020");

        return configuration;
    }

    @Bean
    public FileSystem fileSystem() throws IOException {
        return FileSystem.get(URI.create("hdfs://namenode:8020"), hadoopConfiguration());
    }
}
