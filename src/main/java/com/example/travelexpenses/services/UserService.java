package com.example.travelexpenses.services;

import com.example.travelexpenses.dto.Login;
import com.example.travelexpenses.models.User;

public interface UserService {
    public Boolean save(User user);

    public Object login(Login login);
}
