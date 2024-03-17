package vn.unigap.api.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.unigap.api.entity.Seeker;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeekerDtoOut {

    private Long id;

    private String name;

    private LocalDate birthday;

    private String address;

    private Integer provinceId;

    private String provinceName;

    public static SeekerDtoOut from(Seeker seeker, String provinceName) {
        return SeekerDtoOut.builder()
                .id(seeker.getId())
                .name(seeker.getName())
                .birthday(seeker.getBirthday())
                .address(seeker.getAddress())
                .provinceId(seeker.getProvince())
                .provinceName(provinceName)
                .build();
    }
}