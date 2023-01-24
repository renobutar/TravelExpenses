package com.example.travelexpenses.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.travelexpenses.dto.Login;
import com.example.travelexpenses.dto.*;
import com.example.travelexpenses.models.*;
import com.example.travelexpenses.services.EmployeeService;
import com.example.travelexpenses.services.RoleService;
import com.example.travelexpenses.services.UserService;

@RestController
@RequestMapping("/api/user-management")
public class RestUserManagementController {
    private EmployeeService employeeService;
    private UserService userService;
    private RoleService roleService;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;

    public RestUserManagementController(
            EmployeeService employeeService,
            UserService userService,
            RoleService roleService,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder) {
        this.employeeService = employeeService;
        this.userService = userService;
        this.roleService = roleService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable(required = true) Integer id) {
        return employeeService.getById(id);
    }

    @PostMapping("/register")
    public ResponseEntity<?> regis(@RequestBody Register register) {
        Boolean resultEmployee;
        Boolean resultUser;
        Employee emp = new Employee();
        emp.setFullname(register.getFullname());
        emp.setEmail(register.getEmail());
        emp.setBirthdate(register.getBirthdate());
        emp.setAddress(register.getAddress());
        resultEmployee = employeeService.save(emp);

        User user = new User();
        user.setId(employeeService.getIdByEmail(register.getEmail()));

        Role role = new Role();
        role.setId(roleService.getIdByLevel());
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        resultUser = userService.save(user);

        if (resultEmployee == true && resultUser == true) {
            return ResponseEntity.ok().body("Registrasi Sukses");
        } else {
            return ResponseEntity.badRequest().body("Registrasi Gagal");
        }
    }

    @PostMapping("/check")
    public ResponseEntity<?> login(@RequestBody Login login) {
        org.springframework.security.core.Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (authentication.isAuthenticated() == true) {
            return ResponseEntity.ok().body("Login Sukses");
        } else {
            return ResponseEntity.badRequest().body("Login Gagal");
        }
    }

}
