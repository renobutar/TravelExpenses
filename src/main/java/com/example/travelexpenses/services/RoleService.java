package com.example.travelexpenses.services;

import java.util.List;

import com.example.travelexpenses.models.Role;;

public interface RoleService {
    public List<Role> getAll();

    public Role getById(Integer id);

    public Boolean save(Role role);

    public Integer getIdByLevel();
}
