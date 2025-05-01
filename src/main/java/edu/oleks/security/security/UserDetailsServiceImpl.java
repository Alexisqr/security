package edu.oleks.security.security;

import edu.oleks.security.user.UserRepository;
import edu.oleks.security.user.User;
import edu.oleks.security.user.Role;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/*
@author   oleksandra
@project   security
@class  UserDetailsServiceImpl
@version  1.0.0
@since 01.05.2025 - 16.22
*/

@Service

public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;
    // private final PasswordEncoder passwordEncoder;
    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
//        this.passwordEncoder = passwordEncoder;
    }



//    @PostConstruct
//    void init() {
//        User user = new User();
//        user.setFirstName("John");
//        user.setLastName("Lennon");
//        user.setEmail("john@mail.com");
//        user.setPassword(passwordEncoder.encode("password"));
//        user.setEnabled(true);
//        user.setAccountLocked(false);
//        user.setRoles(List.of(Role.USER));
//        repository.save(user);
//      }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return repository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}