package com.jkbjhs.postgresdemo.repository;

import com.jkbjhs.postgresdemo.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
//    List<Workout> findByUserId(Long userId);
    List<Workout> findByUser_Uid(String userUid);
//    Workout findFirstByUser_UidOrderByEnd_dateAsc(String userUid);
//    Workout findFirstByUser_UidOrderByEnd_dateAsc(String userUid);
}
