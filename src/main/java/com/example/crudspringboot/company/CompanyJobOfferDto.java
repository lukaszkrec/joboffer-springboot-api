package com.example.crudspringboot.company;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class CompanyJobOfferDto {

    private Long id;
    private String title;
    private Double minSalary;
    private Double maxSalary;
    private String location;
}
