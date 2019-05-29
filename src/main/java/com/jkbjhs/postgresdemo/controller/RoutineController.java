package com.jkbjhs.postgresdemo.controller;

import com.jkbjhs.postgresdemo.exception.ResourceNotFoundException;
import com.jkbjhs.postgresdemo.model.Routine;
import com.jkbjhs.postgresdemo.repository.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RoutineController {

    @Autowired
    private RoutineRepository routineRepository;

    @GetMapping("/routines")
    public List<Routine> getRoutines() {
        return routineRepository.findAll();
    }

    @PostMapping("/routines")
    public Routine createRoutine(@Valid @RequestBody Routine routine) {
//        routineRepository.flush();
        return routineRepository.save(routine);
    }

    @DeleteMapping("/routines/{routine_id}")
    public ResponseEntity<?> deleteRoutine(@PathVariable Long routine_id) {
        if(!routineRepository.existsById(routine_id)) {
            throw new ResourceNotFoundException("Routine not found with id: " + routine_id);
        }

        return routineRepository.findById(routine_id)
                .map(routine -> {
                    routineRepository.delete(routine);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Routine not found with id: " + routine_id));
    }

    @PutMapping("/routines/{routine_id}")
    public Routine updateRoutine(@PathVariable Long routine_id, @Valid @RequestBody Routine routineRequest) {
        return routineRepository.findById(routine_id)
                .map(routine -> {
                    routine.setExercises_sets(routineRequest.getExercises_sets());
                    routine.setName(routineRequest.getName());
                    routine.setNotes(routineRequest.getNotes());
                    routine.setUser(routineRequest.getUser());
                    return routineRepository.save(routine);
                }).orElseThrow(() -> new ResourceNotFoundException("Routine not found with id: " + routine_id));
    }

    @GetMapping("/routines/user/{user_uid}")
    public List<Routine> getRoutineByUser(@PathVariable String user_uid) {
        return routineRepository.findByUser_Uid(user_uid);
    }

}
