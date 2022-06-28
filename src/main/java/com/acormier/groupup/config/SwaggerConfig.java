package com.acormier.groupup.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * Class creates API documentation used for outside sources that wish to pull data from the website
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket groupupApi() {
    	//Docket stands for a summary of other brief statement of the contents of a document
    	//helps configure a subset of the services to be documented and groups them by name
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());//returns an object of API info
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Group Up API")
                .version("1.0")
                .description("API for Group Up")
                .contact(new Contact("Alex Cormier", "alex.cormier.herokuapp.com", "a1ccormier@gmail.com"))
                .license("Apache License Version 2.0")
                .build();
    }
}