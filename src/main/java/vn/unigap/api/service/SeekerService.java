package vn.unigap.api.service;

import vn.unigap.api.dto.in.SeekerDtoIn;
import vn.unigap.api.dto.out.SeekerDtoOut;

import java.util.List;

public interface SeekerService {
    SeekerDtoOut createSeeker(SeekerDtoIn seekerDtoIn);
    List<SeekerDtoOut> getAllSeekers();
    SeekerDtoOut getSeekerById(Long id);
    SeekerDtoOut updateSeeker(Long id, SeekerDtoOut seekerDtoOut);
    void deleteSeeker(Long id);
}