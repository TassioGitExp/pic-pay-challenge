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
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(unique = true)
    private String cnpj;

    private String name;
    private Double balance;

    @OneToMany(mappedBy = "store")
    private List<Transaction> transactions;
}
