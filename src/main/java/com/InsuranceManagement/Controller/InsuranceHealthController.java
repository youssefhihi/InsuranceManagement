package com.InsuranceManagement.Controller;

import com.InsuranceManagement.Service.Interfaces.InsuranceHealthService;
import com.InsuranceManagement.dto.request.InsuranceHealthRequestDto;
import com.InsuranceManagement.dto.response.InsuranceHealthResponseDto;
import com.InsuranceManagement.dto.response.UserResponseDto;
import com.InsuranceManagement.entity.User;
import com.InsuranceManagement.utils.CloudinaryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;
@Controller
@RequestMapping("/health-insurance")
public class InsuranceHealthController {

    private final InsuranceHealthService insuranceHealthService;

    @Autowired
    public InsuranceHealthController(InsuranceHealthService insuranceHealthService, CloudinaryService cloudinaryService) {
        this.insuranceHealthService = insuranceHealthService;
    }

    @GetMapping
    public ModelAndView healthInsurance() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(" health "+authentication.isAuthenticated());
//        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
//            return new ModelAndView("redirect:/auth/login");
//        }

        String email = authentication.getName();

        ModelAndView modelAndView = new ModelAndView("healthInsurance");
        List<InsuranceHealthResponseDto> insurancesHealth = insuranceHealthService.getInsuranceHealthByUserId(email);
        modelAndView.addObject("insurancesHealth", insurancesHealth);

        return modelAndView;
    }

    @PostMapping("/create-healthInsurance")
    public String createInsuranceHealth(@ModelAttribute InsuranceHealthRequestDto requestDto, RedirectAttributes redirectAttributes) {
        // Get the authenticated user from Spring Security's context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // If the user is not authenticated, redirect to the login page
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            return "redirect:/auth/login";
        }

        if (requestDto == null) {
            return "redirect:/health-insurance";
        }

        // Get the authenticated user (could use the username to fetch user info)
        String username = authentication.getName();
        User user = new User();
        user.setEmail(username); // Assuming you use username for user identification

        InsuranceHealthRequestDto updatedRequestDto = new InsuranceHealthRequestDto(
                requestDto.age(),
                requestDto.healthStatus(),
                requestDto.coverageType(),
                user
        );

        try {
            insuranceHealthService.createInsuranceHealth(updatedRequestDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "redirect:/health-insurance";
    }

    @GetMapping("/quotation")
    public String calculateInsuranceHealthQuotation(@RequestParam("insurance") UUID insuranceId, Model model) {
        // Get the authenticated user from Spring Security's context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // If the user is not authenticated, redirect to the login page
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            return "redirect:/auth/login";
        }

        InsuranceHealthResponseDto insuranceHealth;
        try {
            insuranceHealth = insuranceHealthService.getInsuranceHealthById(insuranceId);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/health-insurance";
        }

        double amount = insuranceHealthService.CalculateInsuranceHealth(insuranceHealth);
        model.addAttribute("amount", amount);
        model.addAttribute("insuranceHealth", insuranceHealth);

        return "quotation";
    }
}
