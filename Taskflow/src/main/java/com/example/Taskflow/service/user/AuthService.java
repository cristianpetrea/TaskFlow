package com.example.Taskflow.service.user;


import com.example.Taskflow.dto.user.input.LoginRequest;
import com.example.Taskflow.dto.user.output.LoginResponse;
import com.example.Taskflow.model.user.User;
import com.example.Taskflow.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.Taskflow.security.JwtTokenProvider;

@AllArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;


    public LoginResponse authUser(LoginRequest request){
        User user=userRepository.findByEmail(request.getEmail())
                .orElseThrow(()->new BadCredentialsException("Email sau parola incorecta!"));


        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new BadCredentialsException("Email sau parola incorecta!");
        }

        String jwt=tokenProvider.generateToken(user);

        return new LoginResponse(jwt);
    }
}
