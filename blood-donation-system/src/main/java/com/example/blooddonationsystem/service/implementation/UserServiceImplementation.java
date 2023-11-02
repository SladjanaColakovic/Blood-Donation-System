package com.example.blooddonationsystem.service.implementation;

import com.example.blooddonationsystem.dto.ChangePasswordDTO;
import com.example.blooddonationsystem.dto.EditUserDTO;
import com.example.blooddonationsystem.dto.NewUserDTO;
import com.example.blooddonationsystem.exception.InvalidDataException;
import com.example.blooddonationsystem.exception.PasswordIncorrectException;
import com.example.blooddonationsystem.exception.UserEmailExistsException;
import com.example.blooddonationsystem.exception.UserNotFoundException;
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
            throw new InvalidDataException();
        }
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public User register(NewUserDTO newUser) {

        if (UserValidation.isNewUserInvalid(newUser)) {
            throw new InvalidDataException();
        }
        User existUser = userRepository.findByUsername(newUser.getUsername());
        if (existUser != null) {
            throw new UserEmailExistsException();
        }
        User user = modelMapper.map(newUser, User.class);
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User edit(EditUserDTO editUserDTO) {
        if (UserValidation.isEditUserInvalid(editUserDTO)) {
            throw new InvalidDataException();
        }
        User user = userRepository.findByUsername(editUserDTO.getUsername());
        if (user == null) {
            throw new UserNotFoundException();
        }
        user.setAddress(editUserDTO.getAddress());
        user.setCity(editUserDTO.getCity());
        user.setCountry(editUserDTO.getCountry());
        user.setPhoneNumber(editUserDTO.getPhoneNumber());
        return userRepository.save(user);
    }

    @Override
    public User changePassword(ChangePasswordDTO changePassword) {
        if (UserValidation.isChangePasswordInvalid(changePassword)) {
            throw new InvalidDataException();
        }
        User user = userRepository.findByUsername(changePassword.getUsername());
        if (user == null) {
           throw new UserNotFoundException();
        }
        if(!passwordEncoder.matches(changePassword.getOldPassword(), user.getPassword())){
            throw new PasswordIncorrectException();
        }
        user.setPassword(passwordEncoder.encode(changePassword.getPassword()));
        return userRepository.save(user);
    }

}
