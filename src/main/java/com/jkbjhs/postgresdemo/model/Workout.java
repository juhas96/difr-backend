package com.jkbjhs.postgresdemo.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "workout")
public class Workout {
    private static final String SEQ = "RECORD_ID_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ)
    @SequenceGenerator(name = SEQ, sequenceName = SEQ, allocationSize = 1)
    private Long id;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "start_date")
    private Date start_date;

    @Column(name = "end_date")
    private Date end_date;

    @Column(name = "name")
    private String name;

    @Column(name = "notes")
    private String notes;

    @Column(name = "kg_lifted_overall")
    private int kg_lifted_overall;

    @Column(name = "status")
    private int workout_status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Exercise_set> exercises_sets;

}
