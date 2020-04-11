package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TasksIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void shouldGetAllExistingTasks() throws Exception {
        this.mockMvc.perform(get("/api/tasks")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value("test"));
    }

    @Test
    @Order(2)
    public void shouldAddNewTask() throws Exception {
        this.mockMvc.perform(post("/api/tasks")
                .content("{ \"id\" : 2, \"content\" : \"code\" }")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }


    @Test
    @Order(3)
    public void shouldUpdateTaskContentById() throws Exception {
        this.mockMvc.perform(put("/api/tasks/2")
                .content("{ \"content\" : \"code(updated)\" }")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("code(updated)"));
    }
    
    

    @Test
    @Order(4)
    public void shouldDeleteTaskById() throws Exception {
        this.mockMvc.perform(delete("/api/tasks/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }
    @Test
    @Order(5)
    public void shouldUpdateNoContentWhenTaskNotExist() throws Exception {
        this.mockMvc.perform(put("/api/tasks/2")
                .content("{ \"content\" : \"code(updated)\" }")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNoContent());
    }
    @Test
    @Order(6)
    public void shouldDeleteNoContentWhenTaskNotExist() throws Exception {
        this.mockMvc.perform(delete("/api/tasks/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNoContent());
    }
}
