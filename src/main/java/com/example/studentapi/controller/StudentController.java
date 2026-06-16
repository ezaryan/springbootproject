package com.example.studentapi.controller;

import com.example.studentapi.dto.StudentDTO;
import com.example.studentapi.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping
    public StudentDTO createStudent(
            @RequestBody StudentDTO studentDTO) {

        return service.createStudent(studentDTO);
    }

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return service.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentDTO getStudentById(
            @PathVariable Long id) {

        return service.getStudentById(id);
    }

    @PutMapping("/{id}")
    public StudentDTO updateStudent(
            @PathVariable Long id,
            @RequestBody StudentDTO studentDTO) {

        return service.updateStudent(id, studentDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(
            @PathVariable Long id) {

        service.deleteStudent(id);
        return "Student deleted successfully";
    }
}