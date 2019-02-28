package com.zrytech.framework;

import com.zrytech.framework.base.base.BaseApp;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.MultipartConfigElement;


@ServletComponentScan
@SpringBootApplication
@MapperScan({"com.zrytech.framework.*.mapper","com.zrytech.framework.mapper"})
//@ComponentScan({"com.zrytech.framework.*.*", "com.zrytech.framework.*.*"})
//@EnableJpaRepositories({"com.zrytech.framework.*.repository"})
//@EntityScan({"com.zrytech.framework.*.entity"})
@EnableAspectJAutoProxy
@EnableScheduling
public class BaseApplication extends BaseApp {

	public static void main(String[] args) {
		SpringApplication.run(BaseApplication.class, args);
	}

	@Value("${upload.file.dir}")
	private String updatefile;

	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setLocation(updatefile);
		return factory.createMultipartConfig();
	}
}
