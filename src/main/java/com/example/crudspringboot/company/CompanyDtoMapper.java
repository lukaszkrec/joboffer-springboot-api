package com.example.crudspringboot.company;

import org.springframework.stereotype.Service;

@Service
class CompanyDtoMapper {

    CompanyDto map(Company company) {
        return CompanyDto.builder()
                .id(company.getId())
                .name(company.getName())
                .description(company.getDescription())
                .city(company.getCity())
                .employees(company.getEmployees())
                .telephone(company.getTelephone())
                .email(company.getEmail())
                .build();
    }

    Company map(CompanyDto dto) {
        return Company.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .city(dto.getCity())
                .employees(dto.getEmployees())
                .telephone(dto.getTelephone())
                .email(dto.getEmail())
                .build();
    }
}
