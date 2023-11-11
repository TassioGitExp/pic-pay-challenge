package com.study.tassio.picpaychallenge.model.repository;

import com.study.tassio.picpaychallenge.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
