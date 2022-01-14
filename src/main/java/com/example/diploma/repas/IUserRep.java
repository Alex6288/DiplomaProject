package com.example.diploma.repas;

import com.example.diploma.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRep extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
    boolean existsByLogin(String login);
    boolean existsByEmail(String email);
}
