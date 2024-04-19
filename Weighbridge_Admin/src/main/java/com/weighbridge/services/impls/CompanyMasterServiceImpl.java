package com.weighbridge.services.impls;

import com.weighbridge.dtos.CompanyMasterDto;
import com.weighbridge.entities.CompanyMaster;
import com.weighbridge.exceptions.ResourceNotFoundException;
import com.weighbridge.exceptions.ResourceRetrievalException;
import com.weighbridge.repsitories.CompanyMasterRepository;
import com.weighbridge.services.CompanyMasterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyMasterServiceImpl implements CompanyMasterService {

    public final CompanyMasterRepository companyMasterRepository;
    public final ModelMapper modelMapper;
    @Autowired
    HttpServletRequest request;
    @Override
    public CompanyMasterDto createCompany(CompanyMasterDto companyMasterDto) {
        CompanyMaster byCompanyName = companyMasterRepository.findByCompanyName(companyMasterDto.getCompanyName());
        if(byCompanyName!=null){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Company name already exist");
        }
        companyMasterDto.setCompanyId(generateCompanyId(companyMasterDto.getCompanyName()));
        HttpSession session = request.getSession();
        companyMasterDto.setCompanyCreatedBy(String.valueOf(session.getAttribute("userId")));
        companyMasterDto.setCompanyModifiedBy(String.valueOf(session.getAttribute("userId")));
        companyMasterDto.setCompanyCreatedDate(LocalDateTime.now());
        companyMasterDto.setCompanyModifiedDate(LocalDateTime.now());
        CompanyMaster company = modelMapper.map(companyMasterDto, CompanyMaster.class);

        CompanyMaster savedCompany = companyMasterRepository.save(company);

        return modelMapper.map(savedCompany, CompanyMasterDto.class);
    }

    private String generateCompanyId(String companyName) {
        String companyAbbreviation = "";
        if (companyName.length() >= 3) {
            companyAbbreviation = companyName.substring(0, 1).toUpperCase() + companyName.substring(2, 3).toUpperCase();
        } else if (companyName.length() == 2) {
            companyAbbreviation = companyName.substring(0, 1).toUpperCase() + companyName.substring(1, 2).toUpperCase();
        } else {
            // If the company name has only one letter, just take the first letter
            companyAbbreviation = companyName.substring(0, 1).toUpperCase();
        }
        // Concatenate the abbreviation and unique identifier
        long siteCount = companyMasterRepository.countByCompanyNameStartingWith(companyAbbreviation);

        // Generate the site ID based on the count
        String companyId;
        if (siteCount > 0) {
            // If other sites with the same abbreviation exist, append a numeric suffix
            companyId = String.format("%s%02d", companyAbbreviation, siteCount + 1);
        } else {
            // Otherwise, use the abbreviation without a suffix
            companyId = companyAbbreviation+ "01";;
        }

        return companyId;
    }

    @Override
    public List<CompanyMasterDto> getAllCompany() {
        List<CompanyMaster> companies = companyMasterRepository.findAll();
        return companies.stream().map(company -> modelMapper.map(company, CompanyMasterDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<String> getAllCompanyNameOnly() {

        try{
            List<String> allCompanyListName = companyMasterRepository.findAllCompanyListName();
            return allCompanyListName;
        }
        catch (Exception e){
            throw new ResourceNotFoundException("Failed to retrieve roles");
        }

    }
}
