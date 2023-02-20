package com.example.crudspringboot.company;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/companies")
class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/{id}")
    ResponseEntity<CompanyDto> getCompanyById(@PathVariable Long id) {
        return companyService.getCompanyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    ResponseEntity<List<CompanyDto>> getAllCompanies() {
        List<CompanyDto> companyDtos = companyService.allCompanies();
        return ResponseEntity.ok().body(companyDtos);
    }

    @GetMapping("/{id}/offers")
    ResponseEntity<List<CompanyJobOfferDto>> getCompanyOffers(@PathVariable Long id) {
        if (companyService.getJobOfferByCompanyId(id).isEmpty()) {
            return ResponseEntity.notFound().build();

        } else {
            return ResponseEntity.ok(companyService.getJobOfferByCompanyId(id));
        }
    }

    @PostMapping()
    ResponseEntity<CompanyDto> addCompany(@RequestBody CompanyDto companyDto) {
        CompanyDto savedCompany = companyService.addCompany(companyDto);
        URI savedCompanyUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCompany.getId())
                .toUri();
        return ResponseEntity.created(savedCompanyUri).body(savedCompany);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> replaceCompany(@PathVariable Long id, @RequestBody CompanyDto dto) {
        return companyService.replaceCompany(id, dto)
                .map(response -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCompanyById(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }
}
