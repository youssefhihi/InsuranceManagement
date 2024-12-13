package com.InsuranceManagement.Config.security.filter;

import com.InsuranceManagement.Service.Impl.UserActivityServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UserActivityFilter extends OncePerRequestFilter {

    private final UserActivityServiceImpl userActivityService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = (authentication != null && authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User user)
                ? user.getUsername()
                : "Anonymous";
        String action = request.getMethod() + " " + request.getRequestURI();

        userActivityService.logActivity(email, action);

        filterChain.doFilter(request, response);
    }
}
