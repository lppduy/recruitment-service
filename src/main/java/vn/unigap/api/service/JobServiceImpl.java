package vn.unigap.api.service;

import org.springframework.beans.factory.annotation.Autowired;
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
        return null;
    }

    @Override
    public JobDtoOut getJob(Long id) {
        return null;
    }

    @Override
    public void deleteJob(Long id) {

    }

    @Override
    public PageDtoOut<JobDtoOut> getAllJobs(PageDtoIn pageDtoIn) {
        return null;
    }

}
