package com.perscholas.groupupspringbootbackend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import com.perscholas.groupupspringbootbackend.config.SwaggerConfig;


@SpringBootApplication
@EnableAsync
@Import(SwaggerConfig.class)//at the application startup spring will invoke springfox and sf is going to scan all the rest controller components in app and corresponding details for the rest controllers and generate the rest api documentation for all of them. 
public class GroupupSpringbootBackendApplication{

	public static void main(String[] args) {
        SpringApplication.run(com.perscholas.groupupspringbootbackend.GroupupSpringbootBackendApplication.class, args);
    }
	

}
