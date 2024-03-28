package vn.unigap.api.controller;

import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unigap.api.dto.in.EmployerDtoIn;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.out.EmployerDtoOut;
import vn.unigap.api.service.EmployerService;
import vn.unigap.common.controller.AbstractResponseController;
import vn.unigap.common.exception.ApiException;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/employers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployerController extends AbstractResponseController {

    private static final Logger logger = LogManager.getLogger(EmployerController.class);

    private final EmployerService employerService;

    @Autowired
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @PostMapping(value = "")
    public ResponseEntity<?> createEmployer(@RequestBody @Valid EmployerDtoIn employerDtoIn) {
        logger.info("Creating employer with data: " + employerDtoIn);
        employerService.createEmployer(employerDtoIn);
        return responseEntity(() -> {
            return new HashMap();
        }, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateEmpployer(@PathVariable(value = "id") Long id,
                                    @RequestBody @Valid EmployerDtoIn employerDtoIn) {
        logger.info("Updating employer with id: " + id);
        employerService.updateEmployer(id, employerDtoIn);
        return responseEntity(() -> {
            return new HashMap();
        }, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getEmployer(@PathVariable(value = "id") Long id) {
        logger.info("Fetching employer with id: " + id);
        return responseEntity(() -> {
            return this.employerService.getEmployer(id);
        });
    }


    @DeleteMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> deleteEmployer(@PathVariable(value = "id") Long id) {
        logger.info("Deleting employer with id: " + id);
        return responseEntity(() -> {
            this.employerService.deleteEmployer(id);
            return new HashMap<>();
        });
    }

    @GetMapping(value = "", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getAllEmployers(@Valid PageDtoIn pageDtoIn) {
        logger.info("Fetching all employers");
        return responseEntity(() -> {
            return this.employerService.getAllEmployers(pageDtoIn);
        });
    }

}