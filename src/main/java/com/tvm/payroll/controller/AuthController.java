package com.tvm.payroll.controller;

import com.tvm.payroll.component.JwtUtil;
import com.tvm.payroll.entity.UserProfile;

import com.tvm.payroll.repository.UserProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping()
public class AuthController {

    @Autowired
    private UserProfileRepository repo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    // ✅ Register a new user
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserProfile user) {

        // Step 1: Validate fields
        if (user.getUsername() == null || user.getUsername().isBlank() ||
                user.getPassword() == null || user.getPassword().isBlank()) {
            return ResponseEntity.badRequest()
                    .body("❌ Username and password cannot be empty!");
        }

        // Step 2: Find all users with same username
        List<UserProfile> usersWithSameName = repo.findAllByUsername(user.getUsername());

        // Step 3: Check if password already registered for same username
        for (UserProfile existingUser : usersWithSameName) {
            if (encoder.matches(user.getPassword(), existingUser.getPassword())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("⚠️ User already registered with same username and password!");
            }
        }

        // Step 4: Encode password and save new user
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);

        return ResponseEntity.ok(" User registered successfully!");
    }

    // ✅ Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserProfile user) {
        return repo.findByUsername(user.getUsername())
                .map(existingUser -> {
                    if (encoder.matches(user.getPassword(), existingUser.getPassword())) {
                        String token = jwtUtil.generateToken(existingUser.getUsername());
                        return ResponseEntity.ok(Map.of("token", token));
                    } else {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .body(Map.of("error", "❌ Invalid password!"));
                    }
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "❌ User not found!")));
    }
}