package com.TaskManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.TaskManagement.Entity.SubTask;

@Repository
public interface SubTaskRepository extends JpaRepository<SubTask, Long> {

}