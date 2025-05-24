package com.RDR2Hunt.Craft.spring_boot.repositories;

import com.RDR2Hunt.Craft.spring_boot.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 *
 * JPA nos facilita algunas funciones CRUD por defecto
 * CREATE, READ, UPDATE, DELETE
 *
 * */

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

    Roles findRoleByName(String name);

}

