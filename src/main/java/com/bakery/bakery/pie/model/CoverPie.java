package com.bakery.bakery.pie.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "coverPies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoverPie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
