package com.example.diploma.repas;

import com.example.diploma.entity.Roles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRolesRep extends CrudRepository<Roles, Long> {
    @Override
    Iterable<Roles> findAll();
    Roles getByRoleName(String name);

    Optional<Roles> findByRoleName(String name);
}
