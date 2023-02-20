package com.example.crudspringboot.company;

import com.example.crudspringboot.joboffer.JobOffer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String city;
    private Integer employees;
    private String telephone;
    private String email;

    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    private List<JobOffer> jobOffers;

}
