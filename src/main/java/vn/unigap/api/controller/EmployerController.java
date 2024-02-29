package vn.unigap.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unigap.api.dto.in.EmployerDtoIn;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.service.EmployerService;
import vn.unigap.common.controller.AbstractResponseController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/employers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployerController extends AbstractResponseController {

    private final EmployerService employerService;

    @Autowired
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @PostMapping(value = "")
    public ResponseEntity<?> createEmployer(@RequestBody @Valid EmployerDtoIn employerDtoIn) {
        employerService.createEmployer(employerDtoIn);
        return responseEntity(() -> {
            return new HashMap();
        }, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateEmpployer(@PathVariable(value = "id") Long id,
                                    @RequestBody @Valid EmployerDtoIn employerDtoIn) {
        return responseEntity(() -> {
            return this.employerService.updateEmployer(id, employerDtoIn);
        });
    }

    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getEmployer(@PathVariable(value = "id") Long id) {
        return responseEntity(() -> {
            return this.employerService.getEmployer(id);
        });
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> deleteEmployer(@PathVariable(value = "id") Long id) {
        return responseEntity(() -> {
            this.employerService.deleteEmployer(id);
            return new HashMap<>();
        });
    }

    @GetMapping(value = "", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getAllEmployers(@Valid PageDtoIn pageDtoIn) {

        return responseEntity(() -> {
            return this.employerService.getAllEmployers(pageDtoIn);
        });
    }

}
