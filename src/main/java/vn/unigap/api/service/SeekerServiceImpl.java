package vn.unigap.api.service.impl;

import org.springframework.stereotype.Service;
import vn.unigap.api.dto.in.SeekerDtoIn;
import vn.unigap.api.dto.out.SeekerDtoOut;
import vn.unigap.api.entity.Seeker;
import vn.unigap.api.repository.SeekerRepository;
import vn.unigap.api.service.SeekerService;

import java.util.List;

@Service
public class SeekerServiceImpl implements SeekerService {

    private final SeekerRepository seekerRepository;

    public SeekerServiceImpl(SeekerRepository seekerRepository) {
        this.seekerRepository = seekerRepository;
    }

    @Override
    public SeekerDtoOut createSeeker(SeekerDtoIn seekerDtoIn) {

        Seeker savedSeeker = seekerRepository.save(
                new Seeker().builder()
                        .name(seekerDtoIn.getName())
                        .birthday(seekerDtoIn.getBirthday())
                        .address(seekerDtoIn.getAddress())
                        .province(seekerDtoIn.getProvinceId())
                        .build()
        );

        return SeekerDtoOut.from(savedSeeker);
    }

    @Override
    public List<SeekerDtoOut> getAllSeekers() {
        // TODO: Implement this method
        return null;
    }

    @Override
    public SeekerDtoOut getSeekerById(Long id) {
        // TODO: Implement this method
        return null;
    }


    @Override
    public SeekerDtoOut updateSeeker(Long id, SeekerDtoOut seekerDtoOut) {
        // TODO: Implement this method
        return null;
    }

    @Override
    public void deleteSeeker(Long id) {
        // TODO: Implement this method
    }
}