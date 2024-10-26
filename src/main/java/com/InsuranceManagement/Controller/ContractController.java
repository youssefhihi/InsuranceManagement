package com.InsuranceManagement.Controller;

import com.InsuranceManagement.Service.Interfaces.ContractService;
import com.InsuranceManagement.Service.Interfaces.InsuranceService;
import com.InsuranceManagement.Service.Interfaces.MediaService;
import com.InsuranceManagement.dto.request.ContractRequestDto;
import com.InsuranceManagement.dto.response.ContractResponseDto;
import com.InsuranceManagement.entity.Contract;
import com.InsuranceManagement.entity.Insurance;
import com.InsuranceManagement.entity.Media;
import com.InsuranceManagement.mapper.ContractMapper;
import com.InsuranceManagement.utils.CloudinaryService;
import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/contract")
@MultipartConfig
public class ContractController {

    private final ContractService contractService;
    private final CloudinaryService cloudinaryService;
    private final InsuranceService insuranceService;
    private final MediaService mediaService;
    private final ContractMapper contractMapper;

    @Autowired
    public ContractController(ContractService contractService, CloudinaryService cloudinaryService, ContractMapper contractMapper, InsuranceService insuranceService, MediaService mediaService) {
        this.contractService = contractService;
        this.cloudinaryService = cloudinaryService;
        this.insuranceService = insuranceService;
        this.mediaService = mediaService;
        this.contractMapper = contractMapper;
    }

        @RequestMapping(value = "/create", method = RequestMethod.POST)
        public String createContract(
                @ModelAttribute ContractRequestDto requestDto,
                @RequestParam("documents") List<MultipartFile> documents,
                RedirectAttributes redirectAttributes) {

            System.out.println("dsssssqqs"+requestDto);
            if (documents.isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Documents are required.");
                return "redirect:/auto-insurance";
            }
            ContractRequestDto contractRequestDto = requestDto(requestDto, documents);

            Insurance insurance;
            try {
                insurance = insuranceService.getInsurance(contractRequestDto.insuranceId());
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", e.getMessage());
                return "redirect:/auto-insurance";
            }

            if (insurance == null) {
                redirectAttributes.addFlashAttribute("error", "Insurance not found.");
                return "redirect:/auto-insurance";
            }

            // Create a new Contract entity
            Contract contract = contractMapper.toEntity(contractRequestDto);
            contract.setInsurance(insurance); // Set the found insurance

            // Handle media creation if necessary
            List<Media> mediaList = contractRequestDto.documents().stream()
                    .map(url -> new Media(url, contract)) // Assuming Media constructor takes url and contract
                    .collect(Collectors.toList());

            // Save media and contract
            mediaService.createMultipleMedia(mediaList);
            contractService.createContract(contract);

            redirectAttributes.addFlashAttribute("success", "Contract created successfully.");
            return "redirect:/auto-insurance";
        }

        private ContractRequestDto requestDto(ContractRequestDto requestDto, List<MultipartFile> documents) {
            List<String> uploadedFileUrls = documents.stream()
                    .map(file -> {
                        try {
                            return uploadFile(file);
                        } catch (IOException e) {
                            throw new RuntimeException("Failed to upload file: " + file.getOriginalFilename(), e);
                        }
                    })
                    .collect(Collectors.toList());

            return new ContractRequestDto(requestDto.insuranceId(), requestDto.amount(), requestDto.endDate(), uploadedFileUrls);
        }

        private String uploadFile(MultipartFile file) throws IOException {
            try (InputStream inputStream = file.getInputStream()) {
                byte[] fileBytes = inputStream.readAllBytes();
                return cloudinaryService.uploadFile(fileBytes);
            }
        }

}

