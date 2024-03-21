package vn.unigap.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.unigap.api.entity.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    @Query("SELECT r FROM Resume r LEFT JOIN Seeker s ON r.seekerId = s.id ORDER BY s.name")
    Page<Resume> findAllOrderBySeekerName(Pageable pageable);

    @Query("SELECT r FROM Resume r LEFT JOIN Seeker s ON r.seekerId = s.id WHERE r.seekerId = :seekerId ORDER BY s.name")
    Page<Resume> findBySeekerIdOrderBySeekerName(Long seekerId, Pageable pageable);
}
