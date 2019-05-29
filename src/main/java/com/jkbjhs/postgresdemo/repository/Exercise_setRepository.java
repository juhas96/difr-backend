package com.jkbjhs.postgresdemo.repository;

import com.jkbjhs.postgresdemo.model.Exercise_set;
import com.jkbjhs.postgresdemo.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Exercise_setRepository extends JpaRepository<Exercise_set, Long> {
//    List<Exercise_set> findByUser_Uid(String userUid);
//    List<Exercise_set> findTop10ByUser_UidAndExercise_IdOrderByIdDesc(String userUid, Long exerciseId);
//    List<Exercise_set> findFirstBy
//    List<Exercise_set> findByUserUidAndExerciseIdOrderByIdDesc(String userUid, Long exerciseId);
    List<Exercise_set> findTop10ByUserUidAndExerciseIdOrderByIdDesc(String userUid, Long exerciseId);
}
