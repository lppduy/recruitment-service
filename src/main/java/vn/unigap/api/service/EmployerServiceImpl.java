package vn.unigap.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.unigap.api.dto.in.EmployerDtoIn;
import vn.unigap.api.dto.out.EmployerDtoOut;
import vn.unigap.api.entity.Employer;
import vn.unigap.api.entity.Province;
import vn.unigap.api.repository.EmployerRepository;
import vn.unigap.api.repository.ProvinceRepository;
import vn.unigap.common.errorcode.ErrorCode;
import vn.unigap.common.exception.ApiException;

@Service
public class EmployerServiceImpl implements EmployerService {

    private final EmployerRepository employerRepository;
    private final ProvinceRepository provinceRepository;

    @Autowired
    public EmployerServiceImpl(EmployerRepository employerRepository, ProvinceRepository provinceRepository) {
        this.employerRepository = employerRepository;
        this.provinceRepository = provinceRepository;
    }


    @Override
    public EmployerDtoOut createEmployer(EmployerDtoIn employerDtoIn) {

        employerRepository.findByEmail(employerDtoIn.getEmail())
                .ifPresent(employer -> {
                    throw new ApiException(ErrorCode.BAD_REQUEST,
                            HttpStatus.BAD_REQUEST, "email already existed");
                });

        provinceRepository.findById(employerDtoIn.getProvinceId())
                .orElseThrow(() -> new ApiException(ErrorCode.BAD_REQUEST,
                        HttpStatus.BAD_REQUEST, "Invalid provinceId"));

        Employer user = employerRepository.save(Employer.builder()
                .email(employerDtoIn.getEmail())
                .name(employerDtoIn.getName())
                .province(employerDtoIn.getProvinceId())
                .description(employerDtoIn.getDescription())
                .build());

        return EmployerDtoOut.from(user);
    }
}
