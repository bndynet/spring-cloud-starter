package net.bndy.res;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfig {

    private final OpenApiExtensionResolver openApiExtensionResolver;

    @Autowired
    public Knife4jConfig(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("APIs")
                        .description("API documentation")
                        .termsOfServiceUrl("http://bndy.net")
                        .contact(new Contact("Bendy Zhang", "http://bndy.net", "zb@bndy.net"))
                        .version("1.0")
                        .build())
                .groupName("v1.0")
                .select()
                // package to scan the controllers
                .apis(RequestHandlerSelectors.basePackage("net.bndy.res.controllers"))
                .paths(PathSelectors.any())
                .build()
                .extensions((openApiExtensionResolver.buildSettingExtensions()));
        return docket;
    }
}
