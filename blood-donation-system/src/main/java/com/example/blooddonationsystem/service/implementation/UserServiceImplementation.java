package com.example.blooddonationsystem.service.implementation;

import com.example.blooddonationsystem.dto.EditUserDTO;
import com.example.blooddonationsystem.dto.UserDTO;
import com.example.blooddonationsystem.model.User;
import com.example.blooddonationsystem.repository.UserRepository;
import com.example.blooddonationsystem.service.UserService;
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
        return userRepository.findByUsername(username);
    }

    @Override
    public User register(UserDTO userDTO) {

        User existUser = userRepository.findByUsername(userDTO.getUsername());
        if(existUser != null){
            return  null;
        }
        if(!userDTO.getPassword().equals(userDTO.getConfirmPassword())){
            return null;
        }

        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User edit(EditUserDTO editUserDTO) {
        User user = userRepository.findByUsername(editUserDTO.getUsername());

        if(user == null){
            return null;
        }

        if(!editUserDTO.getPassword().equals(editUserDTO.getConfirmPassword())){
            return null;
        }

        User editedUser = editChangedUserInfo(user, editUserDTO);
        return userRepository.save(editedUser);
    }

    private User editChangedUserInfo(User destination, EditUserDTO source){

        if(source.getAddress() != null && !source.getAddress().isBlank()){
            destination.setAddress(source.getAddress());
        }

        if(source.getCity() != null && !source.getCity().isBlank()){
            destination.setCity(source.getCity());
        }

        if(source.getCountry() != null && !source.getCountry().isBlank()){
            destination.setCity(source.getCountry());
        }

        if(source.getPhoneNumber() != null && !source.getPhoneNumber().isBlank()){
            destination.setCity(source.getPhoneNumber());
        }

        if(source.getPassword() != null && !source.getPassword().isBlank()){
            destination.setPassword(passwordEncoder.encode(source.getPassword()));
        }

        return destination;
    }
}
