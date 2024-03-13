package vn.unigap.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import vn.unigap.api.dto.in.EmployerDtoIn;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.out.EmployerDtoOut;
import vn.unigap.api.service.EmployerService;
import vn.unigap.common.errorcode.ErrorCode;
import vn.unigap.common.exception.ApiException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployerControllerTestBackup {

    @Mock
    private EmployerService employerService;

    @InjectMocks
    private EmployerController employerController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void createEmployer_ValidInput_ReturnsCreated() {
        EmployerDtoIn employerDtoIn = new EmployerDtoIn(); // create a valid employer DTO
        EmployerDtoOut employerDtoOut = new EmployerDtoOut(); // create a valid employer DTO
        when(employerService.createEmployer(employerDtoIn)).thenReturn(employerDtoOut);

        ResponseEntity<?> responseEntity = employerController.createEmployer(employerDtoIn);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        verify(employerService, times(1)).createEmployer(employerDtoIn);
    }

    @Test
    void updateEmployer_ValidInput_ReturnsOk() {
        Long id = 1L;
        EmployerDtoIn employerDtoIn = new EmployerDtoIn(); // create a valid employer DTO
        EmployerDtoOut employerDtoOut = new EmployerDtoOut(); // create a valid employer DTO
        when(employerService.updateEmployer(id, employerDtoIn)).thenReturn(employerDtoOut);

        ResponseEntity<?> responseEntity = employerController.updateEmpployer(id, employerDtoIn);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(employerService, times(1)).updateEmployer(id, employerDtoIn);
    }

    @Test
    void getEmployer_ExistingId_ReturnsOk() {
        Long id = 1L;
        EmployerDtoOut employerDtoOut = new EmployerDtoOut(); // create a valid employer DTO
        when(employerService.getEmployer(id)).thenReturn(employerDtoOut);

        ResponseEntity<?> responseEntity = employerController.getEmployer(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(employerService, times(1)).getEmployer(id);
    }

    @Test
    void getEmployer_NonExistingId_ReturnsNotFound() {
        Long id = 1L;
        when(employerService.getEmployer(id)).thenThrow(new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Employer not found"));

        ApiException thrownException = assertThrows(ApiException.class, () -> {
            employerController.getEmployer(id);
        });

        assertEquals(HttpStatus.NOT_FOUND, thrownException.getHttpStatus());
        assertEquals(ErrorCode.NOT_FOUND, thrownException.getErrorCode());
        assertEquals("Employer not found", thrownException.getMessage());
        verify(employerService, times(1)).getEmployer(id);
    }


    @Test
    void deleteEmployer_ExistingId_ReturnsOk() {
        Long id = 1L;

        ResponseEntity<?> responseEntity = employerController.deleteEmployer(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(employerService, times(1)).deleteEmployer(id);
    }

    @Test
    void getAllEmployers_ValidInput_ReturnsOk() {
        PageDtoIn pageDtoIn = new PageDtoIn(); // create a valid page DTO
        when(employerService.getAllEmployers(pageDtoIn)).thenReturn(null);

        ResponseEntity<?> responseEntity = employerController.getAllEmployers(pageDtoIn);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(employerService, times(1)).getAllEmployers(pageDtoIn);
    }
}
