package com.bakery.bakery.pie.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "pies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
