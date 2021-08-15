package br.com.foursales.config;


import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.google.common.collect.Lists;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

    @Bean
    public Docket apiV1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("v1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.foursales.controllers"))
                .build()
                .globalOperationParameters(globalOperationParameters())
                .apiInfo(apiInfo());
    }
    
    private ArrayList<Parameter> globalOperationParameters() {
        return Lists.newArrayList(new ParameterBuilder()
              .name("Authorization")
              .description("Authorization Token")
              .modelRef(new ModelRef("string"))
              .parameterType("header")
              .required(false)
              .build());
    }
    
    private ApiInfo apiInfo() {     
        return new ApiInfoBuilder()
            .title("Foursales")
            .description("Aplicacao teste Foursales")
            .version("1.0")
            .build();       
    }
}
