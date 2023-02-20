package com.example.crudspringboot.company;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
class CompanyService {

    private final CompanyRepository repository;
    private final CompanyDtoMapper companyDtoMapper;
    private final CompanyJobOfferDtoMapper jobOfferDtoMapper;

    public CompanyService(
            CompanyRepository repository,
            CompanyDtoMapper companyDtoMapper,
            CompanyJobOfferDtoMapper jobOfferDtoMapper
    ) {
        this.repository = repository;
        this.companyDtoMapper = companyDtoMapper;
        this.jobOfferDtoMapper = jobOfferDtoMapper;
    }

    Optional<CompanyDto> getCompanyById(Long id) {
        return repository.findById(id)
                .map(companyDtoMapper::map);
    }

    List<CompanyJobOfferDto> getJobOfferByCompanyId(Long companyId) {
        return repository.findById(companyId)
                .map(Company::getJobOffers)
                .orElse(Collections.emptyList())
                .stream()
                .map(jobOfferDtoMapper::map)
                .toList();

    }

    CompanyDto addCompany(CompanyDto dto) {
        Company company = companyDtoMapper.map(dto);
        Company savedCompany = repository.save(company);
        return companyDtoMapper.map(savedCompany);
    }

    Optional<CompanyDto> replaceCompany(Long companyId, CompanyDto companyDto) {
        if (companyDoesNotExist(companyId)) {
            return Optional.empty();

        }
        companyDto.setId(companyId);
        Company companyToUpdate = companyDtoMapper.map(companyDto);
        Company updatedCompany = repository.save(companyToUpdate);
        return Optional.of(companyDtoMapper.map(updatedCompany));
    }

    List<CompanyDto> allCompanies() {
        return findAll().stream()
                .map(companyDtoMapper::map)
                .toList();

    }

    private boolean companyDoesNotExist(Long companyId) {
        return !repository.existsById(companyId);
    }

    private List<Company> findAll() {
        List<Company> companies = new ArrayList<>();
        repository.findAll().forEach(companies::add);
        return companies;
    }

    void deleteCompany(Long id) {
        repository.deleteById(id);
    }
}
