package vn.unigap.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.unigap.api.entity.Seeker;

public interface SeekerRepository extends JpaRepository<Seeker, Long> {
    @Query("SELECT s FROM Seeker s WHERE s.province.id = :provinceId")
    Page<Seeker> findByProvinceId(Long provinceId, Pageable pageable);
}