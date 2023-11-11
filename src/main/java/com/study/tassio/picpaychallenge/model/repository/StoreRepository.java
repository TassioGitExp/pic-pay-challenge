package com.study.tassio.picpaychallenge.model.repository;

import com.study.tassio.picpaychallenge.model.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, String> {
}
