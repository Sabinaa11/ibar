package com.sinam.service;

import com.sinam.model.Task;

import java.util.List;

public interface TaskService {

    /**
     * Создание таска для юзера
     * @param task
     * @param currentUserId id юзера делающего запрос
     */
    void createTaskForUser(Task task, Long currentUserId);

    /**
     * Возвращает таск для определенного юзера
     * @param userId
     * @return
     */
    List<Task> getTasksByUserId(Long userId);

    /**
     * Возвращает все таски
     * @return
     */
    List<Task> getAllTasks();

}