package by.bsuir.flylink.controller;

import by.bsuir.flylink.exception.AppException;
import by.bsuir.flylink.model.Role;
import by.bsuir.flylink.model.RoleName;
import by.bsuir.flylink.model.User;
import by.bsuir.flylink.playload.ApiResponse;
import by.bsuir.flylink.playload.JwtAuthenticationResponse;
import by.bsuir.flylink.playload.LoginRequest;
import by.bsuir.flylink.playload.SignUpRequest;
import by.bsuir.flylink.security.JwtTokenProvider;
import by.bsuir.flylink.service.RoleService;
import by.bsuir.flylink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequestequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestequest.getLoginOrEmail(),
                        loginRequestequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        if (userService.existsByLogin(signUpRequest.getLogin())) {
            return new ResponseEntity<>(new ApiResponse(false, "Login is already taken"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ApiResponse(false, "Email address is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        User user = new User(signUpRequest.getName(), signUpRequest.getEmail(),
                signUpRequest.getLogin(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleService.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set"));

        user.setRoles(Collections.singleton(userRole));
        User result = userService.saveUser(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getLogin()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));

    }


}
