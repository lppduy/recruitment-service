package vn.unigap.api.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import vn.unigap.api.entity.Employer;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployerRepositoryTests {

    @Autowired
    private EmployerRepository employerRepository;

    @Test
    @DisplayName("JUnit test for save employer operation")
    public void givenEmployerObject_whenSave_thenReturnSavedEmployer(){

        //given - precondition or setup
        Employer employer = Employer.builder()
                .email("duy@gmail.com")
                .name("Duy")
                .province(62)
                .description("Duy Tech")
                .build();
        // when - action or the behaviour that we are going test
        Employer savedEmployer = employerRepository.save(employer);

        // then - verify the output
        assertThat(savedEmployer).isNotNull();
        assertThat(savedEmployer.getId()).isGreaterThan(0);
    }

    @DisplayName("JUnit test for get all employer operation")
    @Test
    public void givenEmployersList_whenFindAll_thenEmployersList(){
        // given - precondition or setup
        Employer employer = Employer.builder()
                .email("duy@gmail.com")
                .name("Duy")
                .province(62)
                .description("Duy Tech")
                .build();

        Employer employer1 = Employer.builder()
                .email("phuc@gmail.com")
                .name("Phuc")
                .province(66)
                .description("Duy Tech")
                .build();

        employerRepository.save(employer);
        employerRepository.save(employer1);

        // when -  action or the behaviour that we are going test
        List<Employer> employerList = employerRepository.findAll();

        // then - verify the output
        assertThat(employerList).isNotNull();
        assertThat(employerList.size()).isEqualTo(2);

    }

    @DisplayName("JUnit test for get employer by id operation")
    @Test
    public void givenEmployerObject_whenFindById_thenReturnEmployerObject(){
        // given - precondition or setup
        Employer employer = Employer.builder()
                .email("duy@gmail.com")
                .name("Duy")
                .province(62)
                .description("Duy Tech")
                .build();
        employerRepository.save(employer);

        // when -  action or the behaviour that we are going test
        Employer employerDB = employerRepository.findById(employer.getId()).get();

        // then - verify the output
        assertThat(employerDB).isNotNull();
    }



    @DisplayName("JUnit test for update employer operation")
    @Test
    public void givenEmployerObject_whenUpdateEmployer_thenReturnUpdatedEmployer(){
        // given - precondition or setup
        Employer employer = Employer.builder()
                .email("duy@gmail.com")
                .name("Duy")
                .province(62)
                .description("Duy Tech")
                .build();
        employerRepository.save(employer);

        // when -  action or the behaviour that we are going test
        Employer savedEmployer = employerRepository.findById(employer.getId()).get();
        savedEmployer.setEmail("lufy@gmail.com");
        savedEmployer.setName("Lufy");
        Employer updatedEmployer =  employerRepository.save(savedEmployer);

        // then - verify the output
        assertThat(updatedEmployer.getEmail()).isEqualTo("lufy@gmail.com");
        assertThat(updatedEmployer.getName()).isEqualTo("Lufy");
    }

    // JUnit test for delete employer operation
    @DisplayName("JUnit test for delete employer operation")
    @Test
    public void givenEmployerObject_whenDelete_thenRemoveEmployer(){
        // given - precondition or setup
        Employer employer = Employer.builder()
                .email("duy@gmail.com")
                .name("Duy")
                .province(62)
                .description("Duy Tech")
                .build();
        employerRepository.save(employer);

        // when -  action or the behaviour that we are going test
        employerRepository.deleteById(employer.getId());
        Optional<Employer> employerOptional = employerRepository.findById(employer.getId());

        // then - verify the output
        assertThat(employerOptional).isEmpty();
    }
}
