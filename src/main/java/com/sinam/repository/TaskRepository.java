package com.sinam.repository;

import com.sinam.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> getByUserId(Long userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Task t SET t.status = :status WHERE t.id = :statusId")
    int changeTaskStatus(@Param("status") boolean param, @Param("statusId") Long statusId);

}