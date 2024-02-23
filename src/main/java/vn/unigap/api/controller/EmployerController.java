package vn.unigap.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unigap.api.entity.Employer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/employers")
public class EmployerController {


    @PostMapping
    public ResponseEntity<Employer> createEmployer(@RequestBody Employer employer) {

        Employer employer1 = new Employer(employer.getId(),employer.getEmail(),
                employer.getName(),
                employer.getProvince(),
                employer.getDescription());


        return ResponseEntity.ok(employer1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>  updateEmployer(@PathVariable("id") int id, @RequestBody Employer employer) {

        Employer employer1 = new Employer(1, "employer1@example.com", "Employer 1", 1, "Description 1");
        Employer employer2 = new Employer(2, "employer2@example.com", "Employer 2", 2, "Description 2");
        Employer employer3 = new Employer(3, "employer3@example.com", "Employer 3", 3, "Description 3");

        List<Employer> employers = new ArrayList<>();

        employers.add(employer1);
        employers.add(employer2);
        employers.add(employer3);

        try {
            Employer employerToUpdate = employers.get(id - 1);

            employerToUpdate.setEmail(employer.getEmail());
            employerToUpdate.setName(employer.getName());
            employerToUpdate.setDescription(employer.getDescription());
            employerToUpdate.setProvince(employer.getProvince());
            employerToUpdate.setDescription(employer.getDescription());
            employerToUpdate.setUpdatedAt(LocalDateTime.now());

            return ResponseEntity.ok(employerToUpdate);

        } catch (IndexOutOfBoundsException e) {
            String msg = "Can not found the user with id = " + id;
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployerById(@PathVariable("id") int id) {

        Employer employer1 = new Employer(1, "employer1@example.com", "Employer 1", 1, "Description 1");
        Employer employer2 = new Employer(2, "employer2@example.com", "Employer 2", 2, "Description 2");
        Employer employer3 = new Employer(3, "employer3@example.com", "Employer 3", 3, "Description 3");

        List<Employer> employers = new ArrayList<>();

        employers.add(employer1);
        employers.add(employer2);
        employers.add(employer3);
        int realId = id - 1;

        try {
            Employer employerToFind = employers.get(id - 1);
            return ResponseEntity.ok(employerToFind);
        } catch (IndexOutOfBoundsException e) {
            String msg = "Can not found the user with id = " + id;
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Employer>> getAllEmployers() {

        Employer employer1 = new Employer(1, "employer1@example.com", "Employer 1", 1, "Description 1");
        Employer employer2 = new Employer(2, "employer2@example.com", "Employer 2", 2, "Description 2");
        Employer employer3 = new Employer(3, "employer3@example.com", "Employer 3", 3, "Description 3");

        List<Employer> employers = new ArrayList<>();
        employers.add(employer1);
        employers.add(employer2);
        employers.add(employer3);

        return ResponseEntity.ok(employers);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployer(@PathVariable("id") int id) {

        Employer employer1 = new Employer(1, "employer1@example.com", "Employer 1", 1, "Description 1");
        Employer employer2 = new Employer(2, "employer2@example.com", "Employer 2", 2, "Description 2");
        Employer employer3 = new Employer(3, "employer3@example.com", "Employer 3", 3, "Description 3");

        List<Employer> employers = new ArrayList<>();

        employers.add(employer1);
        employers.add(employer2);
        employers.add(employer3);
        int realId = id - 1;

        try {
            Employer employerToFind = employers.get(id - 1);
            return ResponseEntity.ok("Deleted successfully the employer with id = " + id);
        } catch (IndexOutOfBoundsException e) {
            String msg = "Can not found the user with id = " + id;
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }

    }

}

