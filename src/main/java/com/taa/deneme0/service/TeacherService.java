package com.taa.deneme0.service;

import com.taa.deneme0.exception.ResourceNotFoundException;
import com.taa.deneme0.model.Teacher;
import com.taa.deneme0.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Teacher create(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher getById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + id));
    }

    public List<Teacher> listAll() {
        return teacherRepository.findAll();
    }

    public Teacher update(Long id, Teacher updated) {
        Teacher t = getById(id);
        t.setName(updated.getName());
        t.setEmail(updated.getEmail());
        t.setSubject(updated.getSubject());
        return teacherRepository.save(t);
    }

    public void delete(Long id) {
        Teacher t = getById(id);
        teacherRepository.delete(t);
    }
}

