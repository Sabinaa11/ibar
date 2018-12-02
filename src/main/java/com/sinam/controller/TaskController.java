package com.sinam.controller;

import com.sinam.model.Task;
import com.sinam.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks/")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping("save")
    public void saveTask(@RequestBody Task task, Long currentUserId) {
        service.createTaskForUser(task, currentUserId);
    }

    @GetMapping("get_user_tasks/{userId}")
    public List<Task> getTasksByUserId(@PathVariable Long userId) {
        return service.getTasksByUserId(userId);
    }

    @GetMapping("all")
    public List<Task> getAllTasks() {
        return service.getAllTasks();
    }
}