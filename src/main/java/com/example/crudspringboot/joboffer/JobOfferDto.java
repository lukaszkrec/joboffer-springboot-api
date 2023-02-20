package com.example.crudspringboot.joboffer;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Builder
class JobOfferDto {

    @Setter(AccessLevel.NONE)
    private Long id;
    private String title;
    private String description;
    private String requirements;
    private String duties;
    private String location;
    private Double minSalary;
    private Double maxSalary;
    private LocalDateTime dateAdded;
    private Long companyId;
    private String companyName;


}
