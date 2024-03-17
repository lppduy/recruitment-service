package vn.unigap.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.unigap.api.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
    Page<Job> findByEmployerId(Long employerId, Pageable pageable);
}
