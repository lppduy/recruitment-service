package vn.unigap.api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/employers")
public class EmployerController {

    @PostMapping
    public String createEmployer() {
        return "Created new employer successfully.";
    }

    @PutMapping("/{id}")
    public String updateEmployer(@PathVariable("id") int id) {
        return "Updated successfully the employer with id = " + id;
    }

    @GetMapping("/{id}")
    public String getEmployerById(@PathVariable("id") int id) {
     return "The employer with id = " + id;
    }

    @GetMapping
    public String getAllEmployers() {
        return "A list of all employers";
    }

    @DeleteMapping("/{id}")
    public String deleteEmployer(@PathVariable("id") int id) {
     return "Deleted successfully the employer with id = " + id;
    }

}

