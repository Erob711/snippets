package com.snippets.snippets.entities;


import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="snippets")
public class Snippet {

    @Id
    @GeneratedValue
    private String Id;

    @Column
    private String language;

    @Column
    private String code;

}
