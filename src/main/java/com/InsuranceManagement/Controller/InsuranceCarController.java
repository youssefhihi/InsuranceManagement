package com.InsuranceManagement.Controller;

import com.InsuranceManagement.Service.Interfaces.InsuranceCarService;
import com.InsuranceManagement.dto.request.ContractRequestDto;
import com.InsuranceManagement.dto.request.InsuranceCarRequestDto;
import com.InsuranceManagement.dto.response.InsuranceCarResponseDto;
import com.InsuranceManagement.utils.CloudinaryService;
import com.InsuranceManagement.utils.Helpers;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;

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
    public ModelAndView home() {
        return new ModelAndView("autoInsurance");
    }

    @RequestMapping(value = "/calculate-quotation", method = RequestMethod.POST)
    public ModelAndView calculateInsuranceCarQuotation(@ModelAttribute InsuranceCarRequestDto requestDto,HttpSession session) {
        if (requestDto == null) {
            return home();
        }
        InsuranceCarResponseDto responseDto = insuranceCarService.CalculateInsuranceCar(requestDto);

        ModelAndView mav = new ModelAndView("autoInsurance");
        session.setAttribute("insuranceCar",responseDto);
        mav.addObject("insuranceCar", responseDto);
        return mav;
    }

    @RequestMapping(value = "/accept-quotation", method = RequestMethod.POST)
    public ModelAndView acceptInsuranceCar(@ModelAttribute ContractRequestDto contractRequestDto,HttpSession session) {

        InsuranceCarResponseDto insurance = (InsuranceCarResponseDto) session.getAttribute("insuranceCar");
        InsuranceCarRequestDto insuranceCarRequestDto = new InsuranceCarRequestDto(
                insurance.driverAge(),
                insurance.model(),
                insurance.brand(),
                insurance.usage(),
                insurance.carType(),
                insurance.hadSinister()
        );
        InsuranceCarResponseDto insuranceResponseDto = insuranceCarService.createInsuranceCar(insuranceCarRequestDto);

        ContractRequestDto contract = new ContractRequestDto(
                insuranceResponseDto.id(),
                contractRequestDto.endDate(),
                insurance.amount()
        );

    return home();
    }

    private String uploadFile(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            byte[] fileBytes = inputStream.readAllBytes();
            return cloudinaryService.uploadFile(fileBytes);
        }
    }
}
