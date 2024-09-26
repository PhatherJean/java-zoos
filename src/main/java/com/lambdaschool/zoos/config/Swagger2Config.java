package com.lambdaschool.zoos.config;

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

@Configuration
@EnableSwagger2
public class Swagger2Config
{
    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors
                .basePackage("com.lambdaschool.zoos"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(apiEndPointInfo());
    }

    private ApiInfo apiEndPointInfo()
    {
        return new ApiInfoBuilder().title("Zoos Module Projects")
                .description("Zoo Module Project")
                .contact( new Contact("Patrice Jean",
                        "http://www.lambdaschol.com",
                        "patrice-jean@lambdastudents.com"))
                .license("MIT")
                .licenseUrl("https://github.com/PhatherJean/java-zoos")
                .version("1.0.0")
                .build();
    }
}
