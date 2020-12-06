package net.bndy.res.config;

import net.bndy.res.interceptor.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${spring.cors.origins}")
    private String[] corsOrigins;

    @Value("${spring.cors.methods}")
    private String[] corsMethods;

    @Value("${spring.cors.maxAge}")
    private int corsMaxAge;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // enable knife4j access
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/api/**")
                .allowedOrigins(this.corsOrigins)
                .allowedMethods(this.corsMethods)
                .maxAge(this.corsMaxAge);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor());
    }
}
