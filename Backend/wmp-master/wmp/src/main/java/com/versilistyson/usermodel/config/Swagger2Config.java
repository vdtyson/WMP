package com.versilistyson.usermodel.config;

import com.fasterxml.classmate.TypeResolver;
import com.versilistyson.usermodel.models.ErrorDetail;
import com.versilistyson.usermodel.models.TokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2Config {
    @Autowired
    private TypeResolver resolver;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.versilistyson"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false) // Allows only my exception responses
                .ignoredParameterTypes(Pageable.class) // allows only my paging parameter list
                .apiInfo(apiEndPointsInfo())
                .pathMapping("/")
                .additionalModels(resolver.resolve(ErrorDetail.class),
                        resolver.resolve(TokenModel.class))
                .ignoredParameterTypes()
                .tags(
                        new Tag("UserEndpoints", "Endpoints driven by users"),
                        new Tag("RolesEndpoints", "Endpoints driven by roles"),
                        new Tag("UseremailEndpoints", "Endpoints driven by Useremails"),
                        new Tag("LogoutEndpoint", "Endpoint for logging out a user"),
                        new Tag("OpenEndpoint", "Endpoints available to all"),
                        new Tag("PlantEndpoints", "Endpoints for plants")
                );

    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Water My Plants API")
                .description("Water my plants project")
                .contact(new Contact("Versilis Tyson",
                        "",
                        "versilistyson@gmail.com"))
                .license("MIT")
                .licenseUrl("https://github.com/Build-weekWMyPlants/Backend/blob/master/LICENSE")
                .version(("1.0.0"))
                .build();
    }
}
