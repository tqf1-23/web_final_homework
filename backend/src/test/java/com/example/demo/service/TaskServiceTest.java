package com.example.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.Times;

import com.example.demo.model.Task;
import com.example.demo.tools.DataTools;

//@SpringBootTest
public class TaskServiceTest {
	@Mock
	private DataTools dataTools;

	@InjectMocks
	private TaskService taskService = new TaskService();
	List<Task> tasks;

	public TaskServiceTest() {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeEach
	void setUp() {
		tasks = new ArrayList<>();
	}

	@Test
	public void shouldGetAllTasks() {
		when(dataTools.readTasksFromFile()).thenReturn(tasks);

		List<Task> all = taskService.getAllTasks();

		assertEquals(tasks, all);
	}

	/* 单元测试不应该调用其他待测试的单元 */
	@Test
	public void shouldAddATask() {
		when(dataTools.readTasksFromFile()).thenReturn(tasks);
		Task newTask = new Task(1, "sleep");
		Task returnTask = taskService.addNewTask(newTask);
		assertEquals(newTask.getId(), returnTask.getId());
		assertEquals(newTask.getContent(), returnTask.getContent());
		assertEquals(newTask.getUpdatedTime(), returnTask.getUpdatedTime());
		verify(dataTools).writeTasksToFile(any());
	}

	@Test
	public void shouldDeleteATask() {
		tasks.add(new Task(1L, "task"));
		when(dataTools.readTasksFromFile()).thenReturn(tasks);

		Optional<Task> deletedTask = tasks.stream().filter(task1 -> task1.getId() == 1L).findAny();
		assertTrue(deletedTask.isPresent());

		Task task = taskService.deleteATask(1L);
		Optional<Task> deletedTask2 = tasks.stream().filter(task1 -> task1.getId() == 1L).findAny();
		assertFalse(deletedTask2.isPresent());
		assertEquals(task.getId(), 1L);
		assertEquals(task.getContent(), "task");
		verify(dataTools).writeTasksToFile(any());
	}

	@Test
	public void shouldNotDeleteWhenNotExists() {
		when(dataTools.readTasksFromFile()).thenReturn(tasks);

		Optional<Task> deletedTask = tasks.stream().filter(task1 -> task1.getId() == 1L).findAny();
		assertFalse(deletedTask.isPresent());

		Task task = taskService.deleteATask(1L);
		Optional<Task> deletedTask2 = tasks.stream().filter(task1 -> task1.getId() == 1L).findAny();
		assertFalse(deletedTask2.isPresent());
		assertNull(task);
		verify(dataTools, new Times(0)).writeTasksToFile(any());
	}
	@Test
	public void shouldUpdateATask() {
		tasks.add(new Task(1L, "task"));
		when(dataTools.readTasksFromFile()).thenReturn(tasks);
		Task task = taskService.update(new Task(1L, "new task"));
		assertEquals(1L, task.getId());
		assertEquals("new task", task.getContent());
		assertNotNull(task.getUpdatedTime());
		verify(dataTools).writeTasksToFile(any());
	}
    @Test
    public void shouldNotUpdateTaskWhenNotExist() {
        when(dataTools.readTasksFromFile()).thenReturn(tasks);
        Task optionalTask =  taskService.update(new Task(1L, "new task"));
        assertNull(optionalTask);
        verify(dataTools, new Times(0)).writeTasksToFile(any());
    }
	
}
