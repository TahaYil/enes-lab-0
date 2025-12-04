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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
    public void createandGetTest() throws Exception {
        Student s1 = new Student("a1", "a2", 12);
        Student s2 = new Student(1L, "a1", "a2", 12);
        when(studentService.create(any(Student.class))).thenReturn(s2);

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(s1)))
                .andExpect(status().isCreated());
        when(studentService.getById(1L)).thenReturn(s2);
        mockMvc.perform(get("/api/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("a1"));
    }
    @Test
    public void ogrenciiSilTesr() throws Exception {
        doNothing().when(studentService).delete(199L);
        mockMvc.perform(delete("/api/students/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(199L)))
                .andExpect(status().isNoContent());


    }

    @Test
    public void createandUpdateTest() throws Exception {
        Student s1 = new Student("a1", "a2", 12);
        Student s2 = new Student(1L, "a1", "a2", 12);
        when(studentService.create(any(Student.class))).thenReturn(s2);
        Student s3= new Student(1L,"b1","b2",13);
        when(studentService.update(any(Long.class),any(Student.class))).thenReturn(s3);
        String r1 = mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(s1)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        Student r1S=objectMapper.readValue(r1,Student.class);
        mockMvc.perform(put("/api/students/"+r1S.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(s3)))
                .andExpect(jsonPath("$.name").value("b1"));

    }

    //aaaa


}
