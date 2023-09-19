package com.example.blooddonationsystem.validation;

import com.example.blooddonationsystem.dto.EditUserDTO;
import com.example.blooddonationsystem.dto.UserDTO;
import com.example.blooddonationsystem.enumeration.Role;

import java.util.regex.Pattern;

public class UserValidation {

    public static boolean isNewUserInvalid(UserDTO newUser) {

        return (newUser.getUsername() == null || newUser.getUsername().equals("") ||
                !Pattern.compile("^(.+)@(\\S+)$").matcher(newUser.getUsername()).matches() ||
                newUser.getPassword() == null || newUser.getPassword().equals("") ||
                !Pattern.compile("^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{8,}$").matcher(newUser.getPassword()).matches() ||
                newUser.getConfirmPassword() == null || newUser.getConfirmPassword().equals("") ||
                newUser.getName() == null || newUser.getName().equals("") ||
                !Pattern.compile("[a-zčćžšđA-ZČĆŽŠĐ ]*").matcher(newUser.getName()).matches() ||
                newUser.getSurname() == null || newUser.getSurname().equals("") ||
                !Pattern.compile("[a-zčćžšđA-ZČĆŽŠĐ ]*").matcher(newUser.getSurname()).matches() ||
                newUser.getAddress() == null || newUser.getAddress().equals("") ||
                newUser.getCity() == null || newUser.getCity().equals("") ||
                newUser.getCountry() == null || newUser.getCountry().equals("") ||
                newUser.getPhoneNumber() == null || newUser.getPhoneNumber().equals("") ||
                !Pattern.compile("^[0-9]+$").matcher(newUser.getPhoneNumber()).matches() ||
                ((newUser.getRole().equals(Role.DONOR)) ? (newUser.getJmbg() == null || newUser.getJmbg().equals("")) : false) ||
                ((newUser.getRole().equals(Role.DONOR)) ? !Pattern.compile("^[0-9]{13,13}$").matcher(newUser.getJmbg()).matches() : false) ||
                newUser.getGender() == null || newUser.getRole() == null ||
                !newUser.getPassword().equals(newUser.getConfirmPassword())

        );
    }

    public static boolean isCurrentUserInvalid(String username) {
        return (username == null || username.equals(""));
    }

    public static boolean isEditUserInvalid(EditUserDTO editUser) {

        return (editUser.getUsername() == null || editUser.getUsername().equals("") ||
                editUser.getAddress() == null || editUser.getAddress().equals("") ||
                editUser.getCity() == null || editUser.getCity().equals("") ||
                editUser.getCountry() == null || editUser.getCountry().equals("") ||
                editUser.getPhoneNumber() == null || editUser.getPhoneNumber().equals("") ||
                !Pattern.compile("^[0-9]+$").matcher(editUser.getPhoneNumber()).matches()
        );
    }
}
