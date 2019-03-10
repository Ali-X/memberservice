package ua.ali_x.memberservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final Contact DEFAULT_CONTACT = new Contact(
            "Alina Neshchebnaya",
            "https://ali-x.github.io/portfolio-landing/src/",
            "alina240895@yandex.ru");

    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "Company RESTful service",
            "Service allows administrators to manage members of the company",
            "1.0", "", DEFAULT_CONTACT, "", "");

    private static final Set<String> DEFAULT_PRODUCES =
            new HashSet<>(Arrays.asList("application/json", "application/xml"));

    private static final Set<String> DEFAULT_CONSUMES =
            new HashSet<>(Arrays.asList("application/json", "application/xml", "multipart/form-data"));

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES)
                .consumes(DEFAULT_CONSUMES);
    }
}