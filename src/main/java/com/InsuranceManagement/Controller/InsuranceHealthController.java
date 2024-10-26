package com.InsuranceManagement.Controller;

import com.InsuranceManagement.Service.Interfaces.InsuranceHealthService;
import com.InsuranceManagement.dto.request.InsuranceHealthRequestDto;
import com.InsuranceManagement.dto.response.InsuranceHealthResponseDto;
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
@RequestMapping("/health-insurance")
public class InsuranceHealthController {
    private final InsuranceHealthService insuranceHealthService;

    @Autowired
    public InsuranceHealthController(InsuranceHealthService insuranceHealthService, CloudinaryService cloudinaryService) {
        this.insuranceHealthService = insuranceHealthService;
    }

    @GetMapping
    public ModelAndView healthInsurance(HttpSession session) {
        UserResponseDto user = (UserResponseDto) session.getAttribute("user");
        if (user == null) {
            return new ModelAndView("redirect:/auth/register");
        }
        ModelAndView modelAndView = new ModelAndView("healthInsurance");
        List<InsuranceHealthResponseDto> insurancesHealth = insuranceHealthService.getInsuranceHealthByUserId(user.id());
        modelAndView.addObject("insurancesHealth", insurancesHealth);
        return modelAndView;

    }



        @RequestMapping(value = "/create-healthInsurance", method = RequestMethod.POST)
        public String createInsuranceHealth(
                @ModelAttribute InsuranceHealthRequestDto requestDto,
                HttpSession session,
                RedirectAttributes redirectAttributes) {
            UserResponseDto userAuth = (UserResponseDto) session.getAttribute("user");
            if (userAuth == null) {
                return "redirect:/auth/register";
            }
            if (requestDto == null) {
                return "redirect:/health-insurance";
            }
            User user = new User();
            user.setId(userAuth.id());

            InsuranceHealthRequestDto updatedRequestDto = new InsuranceHealthRequestDto(
                    requestDto.age(),
                    requestDto.healthStatus(),
                    requestDto.coverageType(),
                    user
            );
            System.out.println(updatedRequestDto);
            try {

                insuranceHealthService.createInsuranceHealth(updatedRequestDto);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return "redirect:/health-insurance";
        }

    @RequestMapping(value = "/quotation", method = RequestMethod.GET)
    public String calculateInsuranceHealthQuotation(@RequestParam("insurance") UUID insuranceId , HttpSession session, Model model) {
        UserResponseDto userAuth = (UserResponseDto) session.getAttribute("user");
        if (userAuth == null) {
            return "redirect:/auth/register";
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
