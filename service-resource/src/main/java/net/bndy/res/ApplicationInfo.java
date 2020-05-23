package net.bndy.res;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Configuration
public class ApplicationInfo {
    @Value("${spring.application.name}")
    public String name;
}
