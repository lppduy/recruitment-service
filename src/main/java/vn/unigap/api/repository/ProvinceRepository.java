package vn.unigap.api.repository;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.unigap.api.entity.Province;

public interface ProvinceRepository extends JpaRepository<Province,Integer> {
}
