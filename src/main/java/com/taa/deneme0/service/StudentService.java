package com.taa.deneme0.service;

import com.taa.deneme0.exception.ResourceNotFoundException;
import com.taa.deneme0.model.Student;
import com.taa.deneme0.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student create(Student student) {
        return studentRepository.save(student);
    }

    public Student getById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    public Student update(Long id, Student updated) {
        Student s = getById(id);
        s.setName(updated.getName());
        s.setEmail(updated.getEmail());
        s.setAge(updated.getAge());
        return studentRepository.save(s);
    }

    public void delete(Long id) {
        Student s = getById(id);
        studentRepository.delete(s);
    }
}

