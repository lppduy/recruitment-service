package vn.unigap.api.dto.out;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.unigap.api.repository.Employer;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployerDtoOut {

    private Long id;
    private String email;
    private String name;
    private int provinceId;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;

    public static EmployerDtoOut from(Employer employer) {
        return EmployerDtoOut.builder()
                .id(employer.getId())
                .email(employer.getEmail())
                .name(employer.getName())
                .provinceId(employer.getProvince())
                .description(employer.getDescription())
                .createdAt(employer.getCreatedAt())
                .updatedAt(employer.getUpdatedAt())
                .build();
    }
}
