package com.jkbjhs.postgresdemo.controller;


import com.jkbjhs.postgresdemo.exception.ResourceNotFoundException;
import com.jkbjhs.postgresdemo.model.Exercise;
import com.jkbjhs.postgresdemo.model.Exercise_set;
import com.jkbjhs.postgresdemo.model.Workout;
import com.jkbjhs.postgresdemo.repository.ExerciseRepository;
import com.jkbjhs.postgresdemo.repository.Exercise_setRepository;
import com.jkbjhs.postgresdemo.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class WorkoutController {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private Exercise_setRepository exercise_setRepository;

    @GetMapping("/workouts")
    public List<Workout> getWorkouts() {
        return workoutRepository.findAll();
    }

    @PostMapping("/workouts")
    public Workout createWorkout(@Valid @RequestBody Workout workout) {
        return workoutRepository.save(workout);
    }

    @DeleteMapping("/workouts/{workout_id}")
    public ResponseEntity<?> deleteWorkout(@PathVariable Long workoutId) {
        if(!workoutRepository.existsById(workoutId)) {
            throw new ResourceNotFoundException("Workout not found with id: " + workoutId);
        }

        return workoutRepository.findById(workoutId)
                .map(workout -> {
                    workoutRepository.delete(workout);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Workout not found with id: " + workoutId));
    }

    @PutMapping("/workouts/{workout_id}")
    public Workout updateWorkout(@PathVariable Long workout_id, @Valid @RequestBody Workout workoutRequest) {
        return workoutRepository.findById(workout_id)
                .map(workout -> {
                    workout.setNotes(workoutRequest.getNotes());
                    workout.setName(workoutRequest.getName());
                    workout.setStart_date(workoutRequest.getStart_date());
                    workout.setEnd_date(workoutRequest.getEnd_date());
                    workout.setKg_lifted_overall(workoutRequest.getKg_lifted_overall());
                    workout.setDuration(workoutRequest.getDuration());
                    workout.setUser(workoutRequest.getUser());
                    return workoutRepository.save(workout);
                }).orElseThrow(() -> new ResourceNotFoundException("Workout not found with id: " + workout_id));
    }

    @GetMapping("/workouts/user/{user_uid}")
    public List<Workout> getWorkoutByUser(@PathVariable String user_uid) {
        return workoutRepository.findByUser_Uid(user_uid);
    }

    @DeleteMapping("/workouts/{workout_id}/exercises_sets/{exercise_setsId}")
    public ResponseEntity<?> deleteExercise(@PathVariable Long workoutId,
                                            @PathVariable Long exercise_setId) {
        if(!workoutRepository.existsById(workoutId)) {
            throw new ResourceNotFoundException("Workout not found with id: " + workoutId);
        }

        return exercise_setRepository.findById(exercise_setId)
                .map(exercise_set -> {
                    exercise_setRepository.delete(exercise_set);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Exercise not found with id: " + exercise_setId));
    }



}
