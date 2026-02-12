package com.TaskManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.TaskManagement.Entity.Epic;

@Repository
public interface EpicRepository extends JpaRepository<Epic, Long>{

}