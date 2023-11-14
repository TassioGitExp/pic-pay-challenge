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
@Table(name = "store", schema = "public")
public class Store {
    @Id
    @Column(name = "store_id")
    private String storeId;

    @Column(unique = true)
    private String cnpj;

    @Column(unique = true)
    private String email;

    private String password;
    private String name;
    private Double balance;

}
