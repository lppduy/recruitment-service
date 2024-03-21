package vn.unigap.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import vn.unigap.api.dto.in.SeekerDtoIn;
import vn.unigap.api.dto.out.SeekerDtoOut;
import vn.unigap.api.service.SeekerService;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SeekerController.class)
public class SeekerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeekerService seekerService;

    @Test
    void givenValidId_whenGetSeeker_thenReturnSeekerAndStatusOk() throws Exception {
        // given
        Long id = 1L;
        SeekerDtoOut expectedSeeker = new SeekerDtoOut();
        expectedSeeker.setId(id);
        expectedSeeker.setName("Duy");
        expectedSeeker.setAddress("123 Street");

        given(seekerService.getSeeker(id)).willReturn(expectedSeeker);

        // when & then
        mockMvc.perform(get("/seekers/{id}", id))
                .andExpect(status().isOk());
    }
}