package com.example.travelexpenses.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.travelexpenses.dto.Login;
import com.example.travelexpenses.models.Employee;
import com.example.travelexpenses.models.User;
import com.example.travelexpenses.repositories.EmployeeRepository;
import com.example.travelexpenses.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Boolean save(User user) {
        userRepository.save(user);
        return userRepository.findById(user.getId()).isPresent();
    }

    // @Override
    // public User findPassword(String password) {
    // return userRepository.findByPassword(password);
    // }

    @Override
    public Object login(Login login) {
        Employee log = employeeRepository.findEmail(login.getEmail());
        if (log.getEmail() != null
                && passwordEncoder.matches(login.getPassword(), log.getUser().getPassword())) {

            return login;
        }

        return false;
    }

}
