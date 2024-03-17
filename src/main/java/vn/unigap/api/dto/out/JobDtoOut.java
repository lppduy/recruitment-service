package vn.unigap.api.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.unigap.api.entity.Job;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDtoOut {

    private Long id;
    private String title;
    private int quantity;
    private String description;
    private Double salary;
    private String fields;
    private String provinces;
    private LocalDate expiredAt;

    public static JobDtoOut from(Job job) {
        JobDtoOut result = JobDtoOut.builder()
                .id(job.getId())
                .title(job.getTitle())
                .quantity(job.getQuantity())
                .description(job.getDescription())
                .salary(job.getSalary())
                .fields(job.getFields())
                .provinces(job.provincesToString())
                .expiredAt(job.getExpiredAt())
                .build();
        return result;
    }
}