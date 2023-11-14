package com.study.tassio.picpaychallenge.model.repository;

import com.study.tassio.picpaychallenge.model.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
