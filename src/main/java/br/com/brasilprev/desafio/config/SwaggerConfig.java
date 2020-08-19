package br.com.brasilprev.desafio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

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
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .apiInfo(apiInfo());
    }

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("BrasilPrev REST API")
				.description("API BrasilPrev - Desafio Java")
				.termsOfServiceUrl("https://github.com/bielfroes/BrasilPrev")
				.contact(new Contact("Gabriel Caio Froes", "",
						"gabrielcaiofroes@gmail.com"))
				.license("MIT License")
				.licenseUrl("https://github.com/andremirandarosa/BrasilPrev/blob/master/LICENSE")
				.version("1.0")
				.build();
	}
}