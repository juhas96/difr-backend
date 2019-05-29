package com.jkbjhs.postgresdemo.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exercise")
public class Exercise {
    private static final String SEQ = "RECORD_ID_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ)
    @SequenceGenerator(name = SEQ, sequenceName = SEQ, allocationSize = 1)
    private Long id;

    @Column(name = "description",columnDefinition = "TEXT")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private int category;

    @Column(name = "img_url", columnDefinition = "TEXT")
    private String img_url;

    @Column(name = "type")
    private String type;
}
