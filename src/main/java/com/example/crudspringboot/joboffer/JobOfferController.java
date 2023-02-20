package com.example.crudspringboot.joboffer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/offers")
class JobOfferController {

    private final JobOfferService jobOfferService;
    private final ObjectMapper objectMapper;

    public JobOfferController(JobOfferService jobOfferService, ObjectMapper objectMapper) {
        this.jobOfferService = jobOfferService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/{id}")
    ResponseEntity<JobOfferDto> getOfferById(@PathVariable Long id) {
        return jobOfferService.getOfferById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<JobOfferDto> saveOffer(@RequestBody JobOfferDto dto) {
        JobOfferDto jobOfferDto = jobOfferService.saveOffer(dto);
        URI savedCompanyUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(jobOfferDto.getId())
                .toUri();
        return ResponseEntity.created(savedCompanyUri).body(jobOfferDto);
    }

    @PatchMapping("{id}")
    ResponseEntity<?> updateJobOffer(@PathVariable Long id, @RequestBody JsonMergePatch jsonMergePatch) {
        try {
            JobOfferDto jobOfferDto = jobOfferService.getOfferById(id).orElseThrow();
            JobOfferDto jobOfferPatched = applyPatchToJobOffer(jsonMergePatch, jobOfferDto);
            jobOfferService.updateOffer(jobOfferPatched);
            return ResponseEntity.ok(jobOfferPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteJobOfferById(@PathVariable Long id) {
        jobOfferService.deleteJobOffer(id);
        return ResponseEntity.noContent().build();
    }


    private JobOfferDto applyPatchToJobOffer(
            JsonMergePatch jsonMergePatch,
            JobOfferDto targetCustomer) throws JsonPatchException, JsonProcessingException {

        JsonNode patched = jsonMergePatch.apply(objectMapper.convertValue(targetCustomer, JsonNode.class));
        return objectMapper.treeToValue(patched, JobOfferDto.class);
    }


}
