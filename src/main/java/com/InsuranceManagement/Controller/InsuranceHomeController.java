package com.InsuranceManagement.Controller;


import com.InsuranceManagement.Service.Interfaces.InsuranceHomeService;
import com.InsuranceManagement.dto.request.InsuranceHomeRequestDto;
import com.InsuranceManagement.dto.response.InsuranceHomeResponseDto;
import com.InsuranceManagement.dto.response.UserResponseDto;
import com.InsuranceManagement.entity.User;
import com.InsuranceManagement.utils.CloudinaryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/home-insurance")
public class InsuranceHomeController {
    private final InsuranceHomeService insuranceHomeService;

    @Autowired
    public InsuranceHomeController(InsuranceHomeService insuranceHomeService, CloudinaryService cloudinaryService) {
        this.insuranceHomeService = insuranceHomeService;
    }

    @GetMapping
    public ModelAndView homeInsurance(HttpSession session) {
        UserResponseDto user = (UserResponseDto) session.getAttribute("user");
        if (user == null) {
            return new ModelAndView("redirect:/auth/login");
        }
        ModelAndView modelAndView = new ModelAndView("homeInsurance");
        List<InsuranceHomeResponseDto> insurancesHome = insuranceHomeService.getInsuranceHomeByUserId(user.id());
        modelAndView.addObject("insurancesHome", insurancesHome);
        return modelAndView;

    }



    @RequestMapping(value = "/create-homeInsurance", method = RequestMethod.POST)
    public String createInsuranceHome(
            @ModelAttribute InsuranceHomeRequestDto requestDto,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        UserResponseDto userAuth = (UserResponseDto) session.getAttribute("user");
        if (userAuth == null) {
            return "redirect:/auth/login";
        }
        if (requestDto == null) {
            return "redirect:/home-insurance";
        }
        User user = new User();
        user.setId(userAuth.id());

        InsuranceHomeRequestDto updatedRequestDto = new InsuranceHomeRequestDto(
                requestDto.propertyValue(),
                requestDto.propertyType(),
                requestDto.isRiskZone(),
                requestDto.hasSecuritySystem(),
                user
        );
        System.out.println(updatedRequestDto);
        try {

            insuranceHomeService.createInsuranceHome(updatedRequestDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/home-insurance";
    }

    @RequestMapping(value = "/quotation", method = RequestMethod.GET)
    public String calculateInsuranceHomeQuotation(@RequestParam("insurance") UUID insuranceId , HttpSession session, Model model) {
        UserResponseDto userAuth = (UserResponseDto) session.getAttribute("user");
        if (userAuth == null) {
            return "redirect:/auth/login";
        }
        InsuranceHomeResponseDto insuranceHome;
        try {

            insuranceHome = insuranceHomeService.getInsuranceHomeById(insuranceId);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/home-insurance";
        }

        double amount = insuranceHomeService.CalculateInsuranceHome(insuranceHome);
        model.addAttribute("amount", amount);
        model.addAttribute("insuranceHome", insuranceHome);
        return "quotation";
    }
}
