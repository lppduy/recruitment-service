package vn.unigap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.unigap.api.entity.Resume;

public interface ResumeRepositotry extends JpaRepository<Resume, Long> {
}
