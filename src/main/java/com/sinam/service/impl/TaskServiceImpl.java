package com.sinam.service.impl;

import com.sinam.exception.WrongPermissionException;
import com.sinam.model.Role;
import com.sinam.model.Task;
import com.sinam.model.User;
import com.sinam.repository.TaskRepository;
import com.sinam.repository.UserRepository;
import com.sinam.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createTaskForUser(Task task, Long currentUserId) {
        Optional<User> user = userRepository.findById(currentUserId);
        if (user.get().getRole().equals(Role.ADMIN)) {
            taskRepository.save(task);
        } else {
            throw new WrongPermissionException("You don't have permission to proceed this operation");
        }
    }

    @Override
    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.getByUserId(userId);
    }

    @Override
    @Transactional
    public List<Task> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();

        tasks.parallelStream()
                .filter(t -> t.getExpiredDate().isBefore(LocalDate.now()) && t.isStatus())
                .forEach(task -> {
                    userRepository.changeUserStatus(false, task.getUserId());
                    taskRepository.changeTaskStatus(false, task.getId());
                    task.setStatus(false);
                });
        return tasks;
    }
}