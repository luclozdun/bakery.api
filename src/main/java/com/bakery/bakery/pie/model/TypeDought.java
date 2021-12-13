package com.bakery.bakery.pie.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "typeDoughts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeDought {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
