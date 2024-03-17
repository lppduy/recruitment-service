package vn.unigap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.unigap.api.entity.Seeker;

public interface SeekerRepository extends JpaRepository<Seeker, Long> {
}