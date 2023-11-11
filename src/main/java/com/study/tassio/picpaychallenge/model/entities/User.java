package com.study.tassio.picpaychallenge.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(unique = true)
    private String cpf;

    private String name;
    private Double balance;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;
}
