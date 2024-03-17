package vn.unigap.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unigap.api.dto.in.EmployerDtoIn;
import vn.unigap.api.dto.in.JobDtoIn;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.out.JobDtoOut;
import vn.unigap.api.service.JobService;
import vn.unigap.common.controller.AbstractResponseController;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping(value = "/jobs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class JobController  extends AbstractResponseController {

    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping(value = "")
    public ResponseEntity<?> createJob(@RequestBody @Valid JobDtoIn jobDtoIn) {
        jobService.createJob(jobDtoIn);
        return responseEntity(() -> {
            return new HashMap();
        }, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateJob(@PathVariable Long id, @RequestBody @Valid JobDtoIn jobDtoIn) {
        jobService.updateJob(id, jobDtoIn);
        return responseEntity(() -> {
            return new HashMap();
        }, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getJob(@PathVariable(value = "id") Long id) {
        return responseEntity(() -> {
            return this.jobService.getJob(id);
        });
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> deleteJob(@PathVariable(value = "id") Long id) {
        return responseEntity(() -> {
            this.jobService.deleteJob(id);
            return new HashMap<>();
        });
    }

    @GetMapping(value = "", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getAllJobs(@Valid PageDtoIn pageDtoIn, @RequestParam(required = false, defaultValue = "-1") Long employerId) {
        return responseEntity(() -> {
            return this.jobService.getAllJobs(pageDtoIn.getPage(), pageDtoIn.getPageSize(), employerId);
        });
    }

}