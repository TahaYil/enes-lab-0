package com.taa.deneme0.controller;

import com.taa.deneme0.model.Student;
import com.taa.deneme0.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> list() {
        return studentService.listAll();
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        Student created = studentService.create(student);
        return ResponseEntity.created(URI.create("/api/students/" + created.getId())).body(created);
    }

    @GetMapping("/{id}")
    public Student get(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student student) {
        return studentService.update(id, student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

