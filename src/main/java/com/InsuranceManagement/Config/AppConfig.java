package com.InsuranceManagement.Config;

import com.InsuranceManagement.Config.security.SecurityConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.InsuranceManagement")
@Import({WebConfig.class, JpaConfig.class, DataSourceConfig.class, SecurityConfig.class})
public class AppConfig {

}
