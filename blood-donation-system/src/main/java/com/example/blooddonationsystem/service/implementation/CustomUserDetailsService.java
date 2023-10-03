package com.example.blooddonationsystem.service.implementation;

import com.example.blooddonationsystem.security.helper.CustomUserDetails;
import com.example.blooddonationsystem.model.User;
import com.example.blooddonationsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("Korisnik nije pronađen '%s'.", username));
        } else {
            return new CustomUserDetails(
                    user.getUsername(),
                    user.getPassword(),
                    user.getRole().toString(),
                    user.getLastPasswordResetDate()
            );
        }
    }
}
