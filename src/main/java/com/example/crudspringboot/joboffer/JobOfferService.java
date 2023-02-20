package com.example.crudspringboot.joboffer;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
class JobOfferService {

    private final JobOfferRepository jobOfferRepository;
    private final JobOfferDtoMapper jobOfferDtoMapper;

    public JobOfferService(JobOfferRepository jobOfferRepository, JobOfferDtoMapper jobOfferDtoMapper) {
        this.jobOfferRepository = jobOfferRepository;
        this.jobOfferDtoMapper = jobOfferDtoMapper;
    }

    Optional<JobOfferDto> getOfferById(Long id) {
        return jobOfferRepository.findById(id)
                .map(jobOfferDtoMapper::map);
    }

    JobOfferDto saveOffer(JobOfferDto dto) {
        JobOffer jobOfferSave = jobOfferDtoMapper.map(dto);
        jobOfferSave.setDateAdded(LocalDateTime.now());
        JobOffer savedJobOffer = jobOfferRepository.save(jobOfferSave);
        return jobOfferDtoMapper.map(savedJobOffer);
    }

    void updateOffer(JobOfferDto dto) {
        JobOffer jobOffer = jobOfferDtoMapper.map(dto);
        jobOfferRepository.save(jobOffer);
    }

    void deleteJobOffer(Long id) {
        jobOfferRepository.deleteById(id);
    }

}
