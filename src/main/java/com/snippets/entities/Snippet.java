package com.snippets.entities;


import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="snippets")
public class Snippet {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String language;

    @Column
    private String code;

}
