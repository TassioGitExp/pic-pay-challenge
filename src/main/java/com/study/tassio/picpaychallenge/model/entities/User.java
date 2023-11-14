package com.study.tassio.picpaychallenge.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "user", schema = "public")
public class User {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    private String name;
    private String password;
    private Double balance;

}
