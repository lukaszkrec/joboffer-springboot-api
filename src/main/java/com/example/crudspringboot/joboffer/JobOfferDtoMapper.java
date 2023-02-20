package com.example.crudspringboot.joboffer;

import com.example.crudspringboot.company.Company;
import com.example.crudspringboot.company.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
class JobOfferDtoMapper {

    private final CompanyRepository repository;

    JobOfferDtoMapper(CompanyRepository repository) {
        this.repository = repository;
    }

    JobOfferDto map(JobOffer jobOffer) {
        return JobOfferDto.builder()
                .id(jobOffer.getId())
                .title(jobOffer.getTitle())
                .description(jobOffer.getDescription())
                .requirements(jobOffer.getRequirements())
                .duties(jobOffer.getDuties())
                .location(jobOffer.getLocation())
                .minSalary(jobOffer.getMinSalary())
                .maxSalary(jobOffer.getMaxSalary())
                .dateAdded(jobOffer.getDateAdded())
                .companyId(jobOffer.getCompany().getId())
                .companyName(jobOffer.getCompany().getName())
                .build();
    }


    JobOffer map(JobOfferDto dto) {
        Company company = repository.findById(dto.getCompanyId()).orElseThrow();
        return JobOffer.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .requirements(dto.getRequirements())
                .duties(dto.getDuties())
                .location(dto.getLocation())
                .minSalary(dto.getMinSalary())
                .maxSalary(dto.getMaxSalary())
                .dateAdded(dto.getDateAdded())
                .company(company)
                .build();


    }
}
