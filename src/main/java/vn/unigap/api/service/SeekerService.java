package vn.unigap.api.service;

import vn.unigap.api.dto.out.SeekerDtoOut;

import java.util.List;

public interface SeekerService {
    List<SeekerDtoOut> getAllSeekers();
    SeekerDtoOut getSeekerById(Long id);
    SeekerDtoOut createSeeker(SeekerDtoOut seekerDtoOut);
    SeekerDtoOut updateSeeker(Long id, SeekerDtoOut seekerDtoOut);
    void deleteSeeker(Long id);
}