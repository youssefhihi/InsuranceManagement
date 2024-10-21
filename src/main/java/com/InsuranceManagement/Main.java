package com.InsuranceManagement;

import com.InsuranceManagement.Config.DataSourceConfig;
import com.InsuranceManagement.Config.JpaConfig;
import com.InsuranceManagement.Service.Interfaces.UserService;
import com.InsuranceManagement.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class, JpaConfig.class);

        UserService userService = context.getBean(UserService.class);

        // Example usage
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");
        user.setAddress("address");
        user.setPhoneNumber("121");
        userService.saveUser(user);
    }
}