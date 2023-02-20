package com.example.crudspringboot.joboffer;

import com.example.crudspringboot.company.Company;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String title;
    private String description;
    private String requirements;
    private String duties;
    private String location;
    private Double maxSalary;
    private Double minSalary;
    private LocalDateTime dateAdded;
    private Integer submissions;

    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id")
    private Company company;


}
