package com.example.demo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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
}
