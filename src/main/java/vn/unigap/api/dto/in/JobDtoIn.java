package vn.unigap.api.dto.in;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.unigap.api.entity.Province;
import vn.unigap.common.ProvincesDeserializer;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDtoIn {

    @NotEmpty
    @Size(max = 255)
    private String title;

    @NotNull
    private Long employerId;

    @NotNull
    private Integer quantity;

    @NotEmpty
    @Size(max = 1000)
    private String description;

    @NotNull
    private String fieldIds;

    @NotNull
    @JsonDeserialize(using = ProvincesDeserializer.class)
    private Set<Province> provinceIds;

    @NotNull
    private Double salary;

    @NotNull
    private LocalDate expiredAt;
}