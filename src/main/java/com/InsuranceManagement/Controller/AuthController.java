package com.InsuranceManagement.Controller;

import com.InsuranceManagement.Service.Interfaces.UserService;
import com.InsuranceManagement.dto.request.UserRequestDto;
import com.InsuranceManagement.dto.response.UserResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/register")
    public ModelAndView register(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("register");
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
                redirectAttributes.addFlashAttribute("success", "Your account has been successfully created. Please log in.");
                return "redirect:/auth/login";
            }
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/register";
    }


    @GetMapping("/login")
    public ModelAndView showLoginForm(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("login");
        Optional<UserResponseDto> userResponseDto = userService.authenticateUser(email, password);
        if (userResponseDto.isPresent()) {
            modelAndView = new ModelAndView("redirect:/home");
            modelAndView.addObject("user", userResponseDto.get());
            session.setAttribute("user", userResponseDto);
            return modelAndView;
        } else {
            modelAndView.addObject("error", "Invalid email or password.");
            return modelAndView;
        }
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return new ModelAndView("redirect:/auth/login");
    }

}
