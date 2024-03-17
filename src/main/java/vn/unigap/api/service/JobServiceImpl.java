package vn.unigap.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.unigap.api.dto.out.JobDtoOut;
import vn.unigap.api.dto.in.JobDtoIn;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.entity.Employer;
import vn.unigap.api.entity.Job;
import vn.unigap.api.repository.EmployerRepository;
import vn.unigap.api.repository.JobRepository;
import vn.unigap.common.errorcode.ErrorCode;
import vn.unigap.common.exception.ApiException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService{
    private final JobRepository jobRepository;
    private final EmployerRepository employerRepository;

    public JobServiceImpl(JobRepository jobRepository, EmployerRepository employerRepository) {
        this.jobRepository = jobRepository;
        this.employerRepository = employerRepository;
    }

    @Override
    public JobDtoOut createJob(JobDtoIn jobDtoIn) {
        Job job = new Job();
        job.setTitle(jobDtoIn.getTitle());

        Employer employer = employerRepository.findById(jobDtoIn.getEmployerId())
                .orElseThrow(
                        () -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Employer not found")
                );

        job.setEmployer(employer);

        job.setTitle(jobDtoIn.getTitle());
        job.setQuantity(jobDtoIn.getQuantity());
        job.setDescription(jobDtoIn.getDescription());
        job.setSalary(jobDtoIn.getSalary());
        job.setFields(jobDtoIn.getFieldIds());
        job.setProvinces(jobDtoIn.getProvinceIds());
        job.setExpiredAt(jobDtoIn.getExpiredAt());

        Job savedJob = jobRepository.save(job);

        return JobDtoOut.from(savedJob);
    }

    @Override
    public JobDtoOut updateJob(Long id, JobDtoIn jobDtoIn) {
        Job job = jobRepository.findById(id)
                .orElseThrow(
                        () -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Job not found")
                );

        job.setTitle(jobDtoIn.getTitle());
        job.setQuantity(jobDtoIn.getQuantity());
        job.setDescription(jobDtoIn.getDescription());
        job.setSalary(jobDtoIn.getSalary());
        job.setFields(jobDtoIn.getFieldIds());
        job.setProvinces(jobDtoIn.getProvinceIds());
        job.setExpiredAt(jobDtoIn.getExpiredAt());

        Employer employer = employerRepository.findById(jobDtoIn.getEmployerId())
                .orElseThrow(
                        () -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Employer not found")
                );

        job.setEmployer(employer);

        Job updatedJob = jobRepository.save(job);

        return JobDtoOut.from(updatedJob);
    }

    @Override
    public JobDtoOut getJob(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(
                        () -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Job not found")
                );

        return JobDtoOut.from(job);
    }

    @Override
    public void deleteJob(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(
                        () -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Job not found")
                );

        jobRepository.delete(job);
    }

    @Override
    public PageDtoOut<JobDtoOut> getAllJobs(PageDtoIn pageDtoIn) {
        Page<Job> jobPage = this.jobRepository.findAll(PageRequest.of(pageDtoIn.getPage() - 1, pageDtoIn.getPageSize(),
                Sort.by("title").ascending()));
        // Tạo đối tượng JobDtoOut từ đối tượng Job
        // Bạn cần thay đổi phương thức này nếu cần thiết
        return PageDtoOut.from(pageDtoIn.getPage(), pageDtoIn.getPageSize(), jobPage.getTotalElements(),
                jobPage.stream().map(JobDtoOut::from).toList());
    }

}
