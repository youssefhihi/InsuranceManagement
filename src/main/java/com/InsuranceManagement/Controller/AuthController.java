package com.InsuranceManagement.Controller;

import com.InsuranceManagement.Service.Interfaces.UserService;
import com.InsuranceManagement.dto.request.UserRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/register")
    public ModelAndView register(HttpSession session) {
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                !"anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("register");
    }

    @GetMapping("/login")
    public ModelAndView showLoginForm() {
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                !"anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("login");
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("userRequestDto") UserRequestDto userRequestDto,
                               @RequestParam String confirmPassword,
                               RedirectAttributes redirectAttributes) {
        if (!userRequestDto.password().equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "Passwords do not match.");
            return "redirect:/auth/register";
        }
        try {
            boolean isRegistered = userService.registerUser(userRequestDto);
            if (isRegistered) {
                redirectAttributes.addFlashAttribute("success", "Account successfully created. Please log in.");
                return "redirect:/auth/login";
            }
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/auth/register";
    }



    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        RedirectAttributes redirectAttributes) {
        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);
            Authentication authentication = authenticationManager.authenticate(authToken);
             SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("dddd" + authentication);
            return "redirect:/";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Invalid email or password.");
            return "redirect:/auth/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        SecurityContextHolder.clearContext();
        return "redirect:/auth/login";
    }
}
