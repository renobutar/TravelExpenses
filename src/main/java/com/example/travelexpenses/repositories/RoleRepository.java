package com.example.travelexpenses.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.travelexpenses.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(value = "select r.id from tbl_m_role r where r.level = (select max(level) from tbl_m_role)", nativeQuery = true)
    Integer getIdByMaxLevel();
}
