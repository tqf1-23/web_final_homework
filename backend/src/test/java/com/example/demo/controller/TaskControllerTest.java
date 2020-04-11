package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.alibaba.fastjson.JSON;
import com.example.demo.model.Task;
import com.example.demo.service.TaskService;

/*主要用于controller层测试，只覆盖应用程序的controller层，
 * HTTP请求和响应是Mock出来的，因此不会创建真正的连接*/
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
	 @Autowired
	    private MockMvc mockMvc;
	/* 创建 MockMvc bean进行模拟接口调用 */
	    @MockBean
	    private TaskService service;

	    private List<Task> tasks = new ArrayList<Task>();
	    @Test
	    public void shouldGetAll() throws Exception {
	        when(service.getAllTasks()).thenReturn(tasks);
	        tasks.add(new Task(1L, "a"));
	        this.mockMvc.perform(get("/api/tasks")).andDo(print()).andExpect(status().isOk())
	                .andExpect(jsonPath("$[0].content").value("a"));
	    }
	    
	    @Test
	    public void shouldAddNewTask() throws Exception {
	        Task newTask = new Task(1L, "new");
	        when(service.addNewTask(newTask)).thenReturn(newTask);
	        this.mockMvc.perform(post("/api/tasks")
	                .contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(newTask)))
	                .andDo(print()).andExpect(status().isCreated());
	    }
	    
	    @Test
	    public void shouldDeleteWhenExist() throws Exception {
	    	Task deletedTask = new Task(2L, "b");
	        when(service.deleteATask(2L)).thenReturn(deletedTask);
	        this.mockMvc.perform(delete("/api/tasks/2")
	                .contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(deletedTask)))
	                .andDo(print()).andExpect(status().isOk())
	                .andExpect(jsonPath("$.content").value("b"));
	    }
	    @Test
	    public void shouldNotDeleteWhenNotExists() throws Exception {
	        when(service.deleteATask(2L)).thenReturn(null);
	        this.mockMvc.perform(delete("/api/tasks/2")).andDo(print()).andExpect(status().isNoContent());
	    }
	    
	    @Test
	    public void shouldUpdateTaskById() throws Exception {
	        Task updated = new Task(1L, "updated");
	        when(service.update(any())).thenReturn(updated);
	        this.mockMvc.perform(put("/api/tasks/1")
	                .contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(updated)))
	                .andDo(print()).andExpect(status().isOk())
	                .andExpect(jsonPath("$.content").value("updated"));
	    }
	    @Test
	    public void shouldNotUpdateWhenNotExists() throws Exception {
	        Task updated = new Task(1L, "updated");
	        when(service.update(any())).thenReturn(null);
	        this.mockMvc.perform(put("/api/tasks/1")
	        		.contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(updated)))
	                .andDo(print()).andExpect(status().isNoContent());
	    }

}
