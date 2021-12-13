package com.bakery.bakery.pie.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "sizePies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SizePie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
