package vn.unigap.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unigap.api.dto.in.SeekerDtoIn;
import vn.unigap.api.dto.out.SeekerDtoOut;
import vn.unigap.api.service.SeekerService;
import vn.unigap.common.controller.AbstractResponseController;

import java.util.HashMap;

@RestController
@RequestMapping("/seekers")
public class SeekerController extends AbstractResponseController {

    private final SeekerService seekerService;

    @Autowired
    public SeekerController(SeekerService seekerService) {
        this.seekerService = seekerService;
    }

    @PostMapping
    public ResponseEntity<?> createSeeker(@RequestBody SeekerDtoIn seekerDtoIn) {
        seekerService.createSeeker(seekerDtoIn);
        return responseEntity(() -> {
            return new HashMap();
        }, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSeeker(@PathVariable Long id, @RequestBody SeekerDtoIn seekerDtoIn) {
        seekerService.updateSeeker(id, seekerDtoIn);
        return responseEntity(() -> {
            return new HashMap();
        }, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSeeker(@PathVariable Long id) {
        return responseEntity(() -> {
            return this.seekerService.getSeeker(id);
        });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSeeker(@PathVariable Long id) {
        return responseEntity(() -> {
            this.seekerService.deleteSeeker(id);
            return new HashMap<>();
        });
    }

    @GetMapping
    public ResponseEntity<?> getAllSeekers(@RequestParam int page, @RequestParam int pageSize, @RequestParam Long provinceId) {
        return responseEntity(() -> {
            return this.seekerService.getAllSeekers(page, pageSize, provinceId);
        });
    }

}