package vn.unigap.api.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import vn.unigap.api.entity.Employer;
import vn.unigap.api.repository.ProvinceRepository;

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
    private String provinceName;
    private String description;
//    private Instant createdAt;
//    private Instant updatedAt;

    public static EmployerDtoOut from(Employer employer, String provinceName) {


        return EmployerDtoOut.builder()
                .id(employer.getId())
                .email(employer.getEmail())
                .name(employer.getName())
                .provinceId(employer.getProvince())
                .provinceName(provinceName)
                .description(employer.getDescription())
//                .createdAt(employer.getCreatedAt())
//                .updatedAt(employer.getUpdatedAt())
                .build();
    }
}
