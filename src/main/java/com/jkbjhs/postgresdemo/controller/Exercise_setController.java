package com.jkbjhs.postgresdemo.controller;


import com.jkbjhs.postgresdemo.exception.ResourceNotFoundException;
import com.jkbjhs.postgresdemo.model.Exercise_set;
import com.jkbjhs.postgresdemo.repository.Exercise_setRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class Exercise_setController {

    @Autowired
    private Exercise_setRepository exercise_setRepository;

    @GetMapping("/exercise_sets")
    public List<Exercise_set> getExercise_sets() {
        return exercise_setRepository.findAll();
    }

//    @GetMapping("/exercise_sets/byuser/{user_uid}&{exercise_id}")
//    public List<Exercise_set> getExercise_setsByUserAndExercise(@PathVariable String user_uid,
//                                                                @PathVariable Long exercise_id) {
//        return exercise_setRepository.findTop10ByUser_UidAndExercise_IdOrderByIdDesc(user_uid,exercise_id);
//

    @GetMapping("/exercise_sets/byUser/{user_uid}&{exercise_id}")
    public List<Exercise_set> getExercise_SetsByUserAndExercise(@PathVariable String user_uid,
                                                                @PathVariable Long exercise_id) {
        return exercise_setRepository.findTop10ByUserUidAndExerciseIdOrderByIdDesc(user_uid,exercise_id);
    }


    @PostMapping("/exercise_sets")
    public Exercise_set createExerciseSet(@Valid @RequestBody Exercise_set exercise_set) {
//        exercise_setRepository.flush();
        return exercise_setRepository.save(exercise_set);
    }

    @DeleteMapping("/exercise_sets/{exercise_set_id}")
    public ResponseEntity<?> deleteExerciseSet(@PathVariable Long exercise_setId) {
        if(!exercise_setRepository.existsById(exercise_setId)) {
            throw new ResourceNotFoundException("Exercise set not found with id: " + exercise_setId);
        }

        return exercise_setRepository.findById(exercise_setId)
                .map(exercise_set -> {
                    exercise_setRepository.delete(exercise_set);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Exercise set not found with id: " + exercise_setId));
    }


}
