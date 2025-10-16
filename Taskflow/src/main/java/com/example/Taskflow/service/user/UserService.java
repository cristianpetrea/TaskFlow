package com.example.Taskflow.service.user;

import com.example.Taskflow.dto.user.input.UserRegisterRequest;
import com.example.Taskflow.dto.user.input.UserUpdateRequest;
import com.example.Taskflow.dto.user.output.UserResponse;
import com.example.Taskflow.exception.EmailAlreadyExistsException;
import com.example.Taskflow.exception.UserNotFoundException;
import com.example.Taskflow.model.user.User;
import com.example.Taskflow.repository.user.UserRepository;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
            throw new EmailAlreadyExistsException("Exista deja un cont inregistrat cu acest email!");
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

    public UserResponse getUserProfile(int userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Utilizatorul nu a fost gasit!"));


        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getRegisterDate()
        );
    }

    public UserResponse getUserByEmailAndMap(String email){
        User user=userRepository.findByEmail(email)
                .orElseThrow(() ->new UserNotFoundException("Utilizatorul nu a fost gasit!"));


        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getRegisterDate()
        );
    }


    public UserResponse updateProfile(String email, UserUpdateRequest request){
        User user=userRepository.findByEmail(email)
                .orElseThrow(()-> new UserNotFoundException("Utilizatorul nu a fost gasit"));

        if(request.getName()!=null&& !request.getName().trim().isEmpty()){
            user.setName(request.getName());
        }


        User updatedUser=userRepository.save(user);

        return new UserResponse(
                updatedUser.getId(),
                updatedUser.getName(),
                updatedUser.getEmail(),
                updatedUser.getRole(),
                updatedUser.getRegisterDate()
        );
    }

}
