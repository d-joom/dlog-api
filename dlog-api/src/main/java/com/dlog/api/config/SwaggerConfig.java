package com.dlog.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration  
@EnableAsync
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer{
	private static final String API_NAME = "DLOG API";
    private static final String API_VERSION = "0.0.1";
    private static final String API_DESCRIPTION = "DLOG API 명세서";
    @Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.dlog.api"))
				.paths(PathSelectors.any())
				.build();
	}
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        System.out.println("******************************Configuring swagger resource handler");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
