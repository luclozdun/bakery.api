package com.bakery.bakery.pie.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "formPies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormPie{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;
}

