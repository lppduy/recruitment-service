package vn.unigap.api.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.unigap.api.dto.in.ResumeDtoIn;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.dto.out.ResumeDtoOut;
import vn.unigap.api.entity.Field;
import vn.unigap.api.entity.Province;
import vn.unigap.api.entity.Resume;
import vn.unigap.api.entity.Seeker;
import vn.unigap.api.repository.FieldRepository;
import vn.unigap.api.repository.ProvinceRepository;
import vn.unigap.api.repository.ResumeRepositotry;
import vn.unigap.api.repository.SeekerRepository;
import vn.unigap.common.errorcode.ErrorCode;
import vn.unigap.common.exception.ApiException;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResumeServiceImpl implements ResumeService {
    private final ResumeRepositotry resumeRepository;
    private final SeekerRepository seekerRepository;
    private final FieldRepository fieldRepository;
    private final ProvinceRepository provinceRepository;

    public ResumeServiceImpl(ResumeRepositotry resumeRepository, SeekerRepository seekerRepository, FieldRepository fieldRepository, ProvinceRepository provinceRepository) {
        this.resumeRepository = resumeRepository;
        this.seekerRepository = seekerRepository;
        this.fieldRepository = fieldRepository;
        this.provinceRepository = provinceRepository;
    }

    @Override
    public ResumeDtoOut createResume(ResumeDtoIn resumeDtoIn) {
        Seeker seeker = seekerRepository.findById(resumeDtoIn.getSeekerId())
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Seeker not found"));

        String fields = resumeDtoIn.getFieldIds().stream()
                .map(id -> fieldRepository.findById(Long.valueOf(id))
                        .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Field not found")))
                .map(Field::getId)
                .map(String::valueOf)
                .collect(Collectors.joining("-", "-", "-"));

        String provinces = resumeDtoIn.getProvinceIds().stream()
                .map(id -> provinceRepository.findById(Math.toIntExact(Long.valueOf(id)))
                        .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Province not found")))
                .map(Province::getId)
                .map(String::valueOf)
                .collect(Collectors.joining("-", "-", "-"));

        Resume resume = Resume.builder()
                .seekerId(resumeDtoIn.getSeekerId())
                .careerObj(resumeDtoIn.getCareerObj())
                .title(resumeDtoIn.getTitle())
                .salary(resumeDtoIn.getSalary().intValue())
                .fields(fields)
                .provinces(provinces)
                .build();

        Resume savedResume = resumeRepository.save(resume);

        return ResumeDtoOut.from(savedResume, seeker.getName());
    }

    @Override
    public ResumeDtoOut updateResume(Long id, ResumeDtoIn resumeDtoIn) {
        return null;
    }

    @Override
    public PageDtoOut<ResumeDtoOut> getResumes(Integer page, Integer size) {
        return null;
    }

    @Override
    public ResumeDtoOut getResume(Long id) {
        return null;
    }

    @Override
    public void deleteResume(Long id) {

    }
}
