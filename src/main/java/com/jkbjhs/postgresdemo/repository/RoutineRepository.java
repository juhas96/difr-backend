package com.jkbjhs.postgresdemo.repository;

import com.jkbjhs.postgresdemo.model.Routine;
import com.jkbjhs.postgresdemo.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoutineRepository extends JpaRepository<Routine,Long> {
    List<Routine> findByUserId(Long userId);
    List<Routine> findByUser_Uid(String userUid);
}
