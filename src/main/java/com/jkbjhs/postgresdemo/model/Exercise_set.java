package com.jkbjhs.postgresdemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exercise_set")
public class Exercise_set {

    private static final String SEQ = "RECORD_ID_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ)
    @SequenceGenerator(name = SEQ, sequenceName = SEQ, allocationSize = 1)
    private Long id;

    @Column(name = "kg")
    private int kg;

    @Column(name = "reps")
    private int reps;

    @OneToOne
    private  Exercise exercise;

    @ManyToOne
//    @JoinColumn(name = "user_id")
    private User user;
}
