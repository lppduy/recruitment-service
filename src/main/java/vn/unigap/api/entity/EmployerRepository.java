package vn.unigap.api.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.unigap.api.repository.Employer;

public interface EmployerRepository extends JpaRepository<Employer,Long> {
}
