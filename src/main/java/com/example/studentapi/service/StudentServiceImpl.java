package com.example.studentapi.service;

import com.example.studentapi.dto.StudentDTO;
import com.example.studentapi.entity.Student;
import com.example.studentapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    private StudentDTO mapToDTO(Student student) {
        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getAge(),
                student.getCourse()
        );
    }

    private Student mapToEntity(StudentDTO dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setCourse(dto.getCourse());

        return student;
    }

    @Override
    public StudentDTO createStudent(StudentDTO dto) {

        Student student = mapToEntity(dto);
        Student savedStudent = repository.save(student);

        return mapToDTO(savedStudent);
    }

    @Override
    public List<StudentDTO> getAllStudents() {

        List<Student> students = repository.findAll();

        return students.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getStudentById(Long id) {

        Student student = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        return mapToDTO(student);
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO dto) {

        Student student = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setCourse(dto.getCourse());

        Student updatedStudent = repository.save(student);

        return mapToDTO(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {

        repository.deleteById(id);
    }
}