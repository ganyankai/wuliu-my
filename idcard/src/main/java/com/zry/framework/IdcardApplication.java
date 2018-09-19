package com.zry.framework;

import com.zrytech.framework.base.base.BaseApp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class IdcardApplication extends BaseApp {

	public static void main(String[] args) {
		SpringApplication.run(IdcardApplication.class, args);
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
