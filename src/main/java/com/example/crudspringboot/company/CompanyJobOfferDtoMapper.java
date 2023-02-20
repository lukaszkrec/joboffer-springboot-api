package com.example.crudspringboot.company;

import com.example.crudspringboot.joboffer.JobOffer;
import org.springframework.stereotype.Service;

@Service
class CompanyJobOfferDtoMapper {

    CompanyJobOfferDto map(JobOffer jobOffer) {
        CompanyJobOfferDto dto = CompanyJobOfferDto.builder()
                .id(jobOffer.getId())
                .title(jobOffer.getTitle())
                .location(jobOffer.getLocation())
                .minSalary(jobOffer.getMinSalary())
                .maxSalary(jobOffer.getMaxSalary())
                .build();
        return dto;
    }
}
