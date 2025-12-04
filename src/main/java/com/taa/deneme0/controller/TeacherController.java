package com.taa.deneme0.controller;

import com.taa.deneme0.model.Teacher;
import com.taa.deneme0.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<Teacher> list() {
        return teacherService.listAll();
    }

    @PostMapping
    public ResponseEntity<Teacher> create(@RequestBody Teacher teacher) {
        Teacher created = teacherService.create(teacher);
        return ResponseEntity.created(URI.create("/api/teachers/" + created.getId())).body(created);
    }

    @GetMapping("/{id}")
    public Teacher get(@PathVariable Long id) {
        return teacherService.getById(id);
    }

    @PutMapping("/{id}")
    public Teacher update(@PathVariable Long id, @RequestBody Teacher teacher) {
        return teacherService.update(id, teacher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teacherService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

