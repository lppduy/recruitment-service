package vn.unigap.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.UriComponentsBuilder;
import vn.unigap.api.dto.in.EmployerDtoIn;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.out.EmployerDtoOut;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.entity.Employer;
import vn.unigap.api.service.EmployerService;
import vn.unigap.common.errorcode.ErrorCode;
import vn.unigap.common.exception.ApiException;
import vn.unigap.common.response.ApiResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class EmployerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployerService employerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenEmployerObject_whenCreateEmployer_thenReturnStatusCode201() throws Exception {
        // given - precondition or setup
        Employer employer = Employer.builder()
                .name("Duy")
                .email("duy@gmail.com")
                .province(1)
                .description("Duy Company")
                .build();

        given(employerService.createEmployer(any(EmployerDtoIn.class)))
                .willReturn(null); // Không trả về bất kỳ đối tượng nào

        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/employers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employer)));

        // then - verify the result or output using assert statements
        response.andDo(print())
                .andExpect(status().isCreated());
    }

    // positive scenario - valid employee id
    // JUnit test for GET employee by id REST API
    @Test
    public void givenEmployerId_whenGetEmployerById_thenReturnEmployerObject() throws Exception{
        // given - precondition or setup
        long employerId = 1L;
        Employer employer = Employer.builder()
                .name("Duy")
                .email("duy@gmail.com")
                .province(1)
                .description("Duy Company")
                .build();
        given(employerService.getEmployer(employerId)).willReturn(EmployerDtoOut.from(employer,"Hồ Chí Minh"));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/employers/{id}", employerId));

// then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.object.name", is(employer.getName())))
                .andExpect(jsonPath("$.object.email", is(employer.getEmail())))
                .andExpect(jsonPath("$.object.provinceId", is(employer.getProvince())))
                .andExpect(jsonPath("$.object.provinceName", is("Hồ Chí Minh")))
                .andExpect(jsonPath("$.object.description", is(employer.getDescription())));
    }

    // negative scenario - invalid employer id
    // JUnit test for GET employer by id REST API
    @Test
    public void givenInvalidEmployerId_whenGetEmployerById_thenReturnNotFound() throws Exception{
        // given - precondition or setup
        long employerId = 1L;
        Employer employer = Employer.builder()
                .name("Duy")
                .email("duy@gmail.com")
                .province(1)
                .description("Duy Company")
                .build();
        given(employerService.getEmployer(employerId))
                .willThrow(new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Employer not found"));


        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/employers/{id}", employerId));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());

    }

    // positive scenario - valid employer id
    @Test
    public void givenValidEmployerId_whenUpdateEmployer_thenReturnUpdatedEmployer() throws Exception {
        // given - precondition or setup
        long employerId = 1L;
        EmployerDtoIn updatedEmployerDto = new EmployerDtoIn();
        updatedEmployerDto.setName("Updated Duy");
        updatedEmployerDto.setEmail("updated_duy@gmail.com");
        updatedEmployerDto.setProvinceId(2);
        updatedEmployerDto.setDescription("Updated Duy Company");

        Employer updatedEmployer = Employer.builder()
                .name(updatedEmployerDto.getName())
                .email(updatedEmployerDto.getEmail())
                .province(updatedEmployerDto.getProvinceId())
                .description(updatedEmployerDto.getDescription())
                .build();

        given(employerService.updateEmployer(employerId, updatedEmployerDto))
                .willReturn(EmployerDtoOut.from(updatedEmployer, "Hồ Chí Minh"));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/employers/{id}", employerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployerDto)));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.object.name", is(updatedEmployer.getName())))
                .andExpect(jsonPath("$.object.email", is(updatedEmployer.getEmail())))
                .andExpect(jsonPath("$.object.provinceId", is(updatedEmployer.getProvince())))
                .andExpect(jsonPath("$.object.provinceName", is("Hồ Chí Minh")))
                .andExpect(jsonPath("$.object.description", is(updatedEmployer.getDescription())));
    }

    // negative scenario - invalid employer id
    @Test
    public void givenInvalidEmployerId_whenUpdateEmployer_thenReturnNotFound() throws Exception {
        // given - precondition or setup
        long employerId = 1L;
        EmployerDtoIn updatedEmployerDto = new EmployerDtoIn();
        updatedEmployerDto.setName("Updated Duy");
        updatedEmployerDto.setEmail("updated_duy@gmail.com");
        updatedEmployerDto.setProvinceId(2);
        updatedEmployerDto.setDescription("Updated Duy Company");

        given(employerService.updateEmployer(employerId, updatedEmployerDto))
                .willThrow(new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Employer not found"));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/employers/{id}", employerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployerDto)));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    // Positive scenario - valid employer id
    @Test
    void givenValidEmployerId_whenDeleteEmployer_thenNoContent() throws Exception {
        // given - precondition or setup
        long employerId = 1L;

        // Mock service để không có lỗi nào được ném ra
        doNothing().when(employerService).deleteEmployer(employerId);

        // when - action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(delete("/employers/{id}", employerId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }

    // Negative scenario - invalid employer id
    @Test
    void givenInvalidEmployerId_whenDeleteEmployer_thenNotFound() throws Exception {
        // given - precondition or setup
        long invalidEmployerId = 999L;

        // Mock service để ném ra một ApiException khi nhận một employer id không hợp lệ
        doThrow(new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Employer not found"))
                .when(employerService).deleteEmployer(invalidEmployerId);

        // when - action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(delete("/employers/{id}", invalidEmployerId));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    // givenListOfEmployers_whenGetAllEmployers_thenReturnEmployersList() => :v ????????????

}






