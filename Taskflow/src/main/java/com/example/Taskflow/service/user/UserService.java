package com.example.Taskflow.service.user;

import com.example.Taskflow.dto.user.UserRegisterRequest;
import com.example.Taskflow.dto.user.UserResponse;
import com.example.Taskflow.models.user.User;
import com.example.Taskflow.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserResponse registerUser(UserRegisterRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("Exista deja un cont inregistrat cu acest email!");
        }

        User newUser=new User();

        String encodedPassword=passwordEncoder.encode(request.getPassword());
        newUser.setEmail(request.getEmail());
        newUser.setName(request.getName());
        newUser.setPassword(encodedPassword);

        newUser.setRole("USER");
        newUser.setRegisterDate(new Timestamp(System.currentTimeMillis()));

        User savedUser=userRepository.save(newUser);

        return new UserResponse(
            savedUser.getId(),
            savedUser.getName(),
            savedUser.getEmail(),
            savedUser.getRole(),
            savedUser.getRegisterDate()
        );
    }

}
