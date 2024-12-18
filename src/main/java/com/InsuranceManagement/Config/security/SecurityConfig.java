package com.InsuranceManagement.Config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{

    private final AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

        //csrf & cors
        http.csrf(AbstractHttpConfigurer::disable).cors(Customizer.withDefaults());

        //http request
        http.authorizeHttpRequests(
                (request) -> {
                    request.requestMatchers("/auth/login", "/auth/register").permitAll();
                    request.requestMatchers("/**").authenticated();
                }
        );

        //storing the session
        http.securityContext((context) -> context.securityContextRepository(securityContextRepository));

        //session management
        http.sessionManagement((session) -> {
                    session.maximumSessions(1).maxSessionsPreventsLogin(true);
                    session.sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::newSession);
                    session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
                }
        );

        //clear cookie when logout
        http.logout((logout) -> {
                    logout.logoutUrl("/api/auth/logout");
                    logout.addLogoutHandler(
                            new HeaderWriterLogoutHandler(
                                    new ClearSiteDataHeaderWriter(ClearSiteDataHeaderWriter.Directive.COOKIES)
                            )
                    );
                    logout.deleteCookies("JSESSIONID");
                }
        );

        //auth provider for connect DAO
        http.authenticationProvider(authenticationProvider);

        return http.build();
    }

}
