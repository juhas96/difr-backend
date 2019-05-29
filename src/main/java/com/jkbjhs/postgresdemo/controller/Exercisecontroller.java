package com.jkbjhs.postgresdemo.controller;


import com.jkbjhs.postgresdemo.exception.ResourceNotFoundException;
import com.jkbjhs.postgresdemo.model.Exercise;
import com.jkbjhs.postgresdemo.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class Exercisecontroller {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @GetMapping("/exercises")
    public List<Exercise> getExercises() {
        return exerciseRepository.findAll();
    }

    @GetMapping("/exercises/{type}")
    public List<Exercise> getExercisesByType(@PathVariable String type) {
        return exerciseRepository.findByTypeContains(type);
    }

//    @GetMapping("/workouts/user/{user_uid}")
//    public List<Workout> getWorkoutByUser(@PathVariable String user_uid) {
//        return workoutRepository.findByUser_Uid(user_uid);
//    }


    @PostMapping("/exercises")
    public Exercise createExercise(@Valid @RequestBody Exercise exercise) {
//        exerciseRepository.flush();
        return exerciseRepository.save(exercise);
    }

    @PutMapping("/exercises/{exerciseId}")
    public Exercise updateExercise(@PathVariable Long exerciseId,
                                   @Valid @RequestBody Exercise exerciseRequest) {
        return exerciseRepository.findById(exerciseId)
                .map(exercise -> {
                    exercise.setCategory(exerciseRequest.getCategory());
                    exercise.setDescription(exerciseRequest.getDescription());
                    exercise.setImg_url(exerciseRequest.getImg_url());
                    exercise.setName(exerciseRequest.getName());
                    return exerciseRepository.save(exercise);
                }).orElseThrow(() -> new ResourceNotFoundException("Exercise not found with id: " + exerciseId));
    }

    @DeleteMapping("exercises/{exerciseId}")
    public ResponseEntity<?> deleteExercise(@PathVariable Long exerciseId) {
        if(!exerciseRepository.existsById(exerciseId)) {
            throw new ResourceNotFoundException("Exercise not found with id: " + exerciseId);
        }

        return exerciseRepository.findById(exerciseId)
                .map(workout -> {
                    exerciseRepository.delete(workout);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Exercise not found with id: " + exerciseId));
    }
}
