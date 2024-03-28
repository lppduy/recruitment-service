package vn.unigap.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unigap.api.dto.in.ResumeDtoIn;
import vn.unigap.api.service.ResumeService;
import vn.unigap.common.controller.AbstractResponseController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/resumes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ResumeController extends AbstractResponseController {

    private final ResumeService resumeService;

    @Autowired
    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @PostMapping(value = "")
    public ResponseEntity<?> createResume(@RequestBody @Valid ResumeDtoIn resumeDtoIn) {
        resumeService.createResume(resumeDtoIn);
        return responseEntity(() -> {
            return new HashMap();
        }, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateResume(@PathVariable Long id, @RequestBody @Valid ResumeDtoIn resumeDtoIn) {
        resumeService.updateResume(id, resumeDtoIn);
        return responseEntity(() -> {
            return new HashMap();
        }, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> deleteResume(@PathVariable Long id) {
        resumeService.deleteResume(id);
        return responseEntity(() -> {
            return new HashMap();
        }, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getResume(@PathVariable(value = "id") Long id) {
        return responseEntity(() -> {
            return this.resumeService.getResume(id);
        });
    }

    @GetMapping(value = "", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getAllResumes(@RequestParam(value = "page", defaultValue = "0") int page,
                                          @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                          @RequestParam(value = "seekerId", required = false) Long seekerId) {
        return responseEntity(() -> {
            return this.resumeService.getAllResumes(page, pageSize, seekerId);
        });
    }
    // ... add more methods for handling other resume-related requests
}