package com.InsuranceManagement.Controller;

import com.InsuranceManagement.Service.Interfaces.ContractService;
import com.InsuranceManagement.Service.Interfaces.InsuranceCarService;
import com.InsuranceManagement.entity.Insurance;
import com.InsuranceManagement.utils.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/contract")
public class ContractController {

    private final ContractService contractService;
    private final CloudinaryService cloudinaryService;
    private final InsuranceCarService insuranceCarService;

    @Autowired
    public ContractController(ContractService contractService, CloudinaryService cloudinaryService, InsuranceCarService insuranceCarService) {
        this.contractService = contractService;
        this.cloudinaryService = cloudinaryService;
        this.insuranceCarService = insuranceCarService;
    }

    @PostMapping
    public String createContract(
            @RequestParam("endDate") String endDate,
            @RequestParam("document") List<MultipartFile> files,
            @RequestParam("insuranceId") UUID insuranceId,
            RedirectAttributes redirectAttributes) {

        // Retrieve the Insurance entity
        Insurance insurance = insuranceService.getInsuranceById(insuranceId);

        if (insurance == null) {
            redirectAttributes.addFlashAttribute("error", "Insurance not found.");
            return "redirect:/auto-insurance";
        }

        // Create the Contract entity
        Contract contract = new Contract();
        contract.setEndDate(endDate);
        contract.setInsurance(insurance);

        // Save the media files
        List<Media> mediaList = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                Media media = new Media();
                media.setDocument(file.getOriginalFilename());
                media.setContract(contract);
                mediaList.add(media);
            }
        }

        contract.setMediaList(mediaList);

        // Save the contract with the associated media
        contractService.saveContract(contract);

        redirectAttributes.addFlashAttribute("success", "Contract created successfully.");
        return "redirect:/auto-insurance";
    }
}
