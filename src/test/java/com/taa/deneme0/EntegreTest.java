package com.taa.deneme0;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taa.deneme0.controller.StudentController;
import com.taa.deneme0.model.Student;
import com.taa.deneme0.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StudentController.class)
public class EntegreTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    StudentService studentService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testEntegre() throws Exception {
        Student s1 = new Student("a1", "a2", 12);
        when(studentService.create(any(Student.class))).thenReturn(s1);

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(s1)))
                .andExpect(status().isCreated());
    }

}
