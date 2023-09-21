package com.example.blooddonationsystem.service.implementation;

import com.example.blooddonationsystem.dto.EditUserDTO;
import com.example.blooddonationsystem.dto.UserDTO;
import com.example.blooddonationsystem.model.User;
import com.example.blooddonationsystem.repository.UserRepository;
import com.example.blooddonationsystem.service.UserService;
import com.example.blooddonationsystem.validation.UserValidation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User findByUsername(String username) {
        if (UserValidation.isCurrentUserInvalid(username)) {
            return null;
        }
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }
        return user;
    }

    @Override
    public User register(UserDTO userDTO) {

        if (UserValidation.isNewUserInvalid(userDTO)) {
            return null;
        }
        User existUser = userRepository.findByUsername(userDTO.getUsername());
        if (existUser != null) {
            return null;
        }
        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User edit(EditUserDTO editUserDTO) {
        if (UserValidation.isEditUserInvalid(editUserDTO)) {
            return null;
        }
        User user = userRepository.findByUsername(editUserDTO.getUsername());
        if (user == null) {
            return null;
        }
        user.setAddress(editUserDTO.getAddress());
        user.setCity(editUserDTO.getCity());
        user.setCountry(editUserDTO.getCountry());
        user.setPhoneNumber(editUserDTO.getPhoneNumber());
        return userRepository.save(user);
    }

}
