package com.example.crudspringboot.company;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class CompanyDto {

    private Long id;
    private String name;
    private String description;
    private String city;
    private Integer employees;
    private String telephone;
    private String email;


}
