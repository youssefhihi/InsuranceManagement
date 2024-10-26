package com.InsuranceManagement.Controller;

import com.InsuranceManagement.Service.Interfaces.InsuranceCarService;
import com.InsuranceManagement.dto.request.InsuranceCarRequestDto;
import com.InsuranceManagement.dto.response.InsuranceCarResponseDto;
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
@RequestMapping("/auto-insurance")
public class InsuranceCarController {

    private final InsuranceCarService insuranceCarService;
    private final CloudinaryService cloudinaryService;
    @Autowired
    public InsuranceCarController(InsuranceCarService insuranceCarService, CloudinaryService cloudinaryService) {
        this.insuranceCarService = insuranceCarService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping
    public ModelAndView autoInsurance(HttpSession session) {
        UserResponseDto user = (UserResponseDto) session.getAttribute("user");
        if (user == null) {
            return new ModelAndView("redirect:/auth/register");
        }
        ModelAndView modelAndView = new ModelAndView("autoInsurance");
        List<InsuranceCarResponseDto> insurancesCar = insuranceCarService.getInsuranceCarByUserId(user.id());
        modelAndView.addObject("insurancesCar", insurancesCar);
        return modelAndView;

    }



    @RequestMapping(value = "/create-autoInsurance", method = RequestMethod.POST)
    public String createInsuranceCar(
            @ModelAttribute InsuranceCarRequestDto requestDto,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        UserResponseDto userAuth = (UserResponseDto) session.getAttribute("user");
        if (userAuth == null) {
            return "redirect:/auth/register";
        }
        if (requestDto == null) {
            return "redirect:/auto-insurance";
        }
        User user = new User();
        user.setId(userAuth.id());
        InsuranceCarRequestDto updatedRequestDto = new InsuranceCarRequestDto(
                requestDto.driverAge(),
                requestDto.model(),
                requestDto.brand(),
                requestDto.usage(),
                requestDto.carType(),
                requestDto.hadSinister(),
                user
        );
        try {

        insuranceCarService.createInsuranceCar(updatedRequestDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/auto-insurance";
    }

    @RequestMapping(value = "/quotation", method = RequestMethod.GET)
    public String calculateInsuranceCarQuotation(@RequestParam("insurance") UUID insuranceId ,HttpSession session, Model model) {
        UserResponseDto userAuth = (UserResponseDto) session.getAttribute("user");
        if (userAuth == null) {
            return "redirect:/auth/register";
        }
        InsuranceCarResponseDto insuranceCar;
        try {

         insuranceCar = insuranceCarService.getInsuranceCarById(insuranceId);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/auto-insurance";
        }

        double amount = insuranceCarService.CalculateInsuranceCar(insuranceCar);
        model.addAttribute("amount", amount);
        model.addAttribute("insuranceCar", insuranceCar);
         return "quotation";
    }



}
