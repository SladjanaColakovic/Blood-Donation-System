package com.example.blooddonationsystem.validation;

import com.example.blooddonationsystem.dto.BloodCenterDTO;
import com.example.blooddonationsystem.dto.EditBloodCenterDTO;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class BloodCenterValidation {

    public static boolean isNewCenterInvalid(BloodCenterDTO newCenter) {

        return (newCenter.getName() == null || newCenter.getName().equals("") ||
                newCenter.getEmail() == null || newCenter.getEmail().equals("") ||
                !Pattern.compile("^(.+)@(\\S+)$").matcher(newCenter.getEmail()).matches() ||
                newCenter.getAddress() == null || newCenter.getAddress().equals("") ||
                newCenter.getCity() == null || newCenter.getCity().equals("") ||
                newCenter.getCountry() == null || newCenter.getCountry().equals("") ||
                newCenter.getPhoneNumber() == null || newCenter.getPhoneNumber().equals("") ||
                !Pattern.compile("^[0-9]+$").matcher(newCenter.getPhoneNumber()).matches() ||
                newCenter.getWorkingTimeFrom() == 0 || newCenter.getWorkingTimeTo() == 0 ||
                newCenter.getCapacity() == 0 || newCenter.getDescription() == null ||
                newCenter.getDescription().equals("")
        );
    }

    public static boolean isManagerBloodCenterInvalid(String managerUsername) {
        return (managerUsername == null || managerUsername.equals(""));
    }

    public static boolean isChangeImageInvalid(Long centerId, MultipartFile image) {
        return (centerId == null || image == null);
    }

    public static boolean isEditCenterInvalid(EditBloodCenterDTO editCenter) {
        return (editCenter.getId() == null ||
                editCenter.getName() == null || editCenter.getName().equals("") ||
                editCenter.getEmail() == null || editCenter.getEmail().equals("") ||
                !Pattern.compile("^(.+)@(\\S+)$").matcher(editCenter.getEmail()).matches() ||
                editCenter.getAddress() == null || editCenter.getAddress().equals("") ||
                editCenter.getCity() == null || editCenter.getCity().equals("") ||
                editCenter.getCountry() == null || editCenter.getCountry().equals("") ||
                editCenter.getPhoneNumber() == null || editCenter.getPhoneNumber().equals("") ||
                !Pattern.compile("^[0-9]+$").matcher(editCenter.getPhoneNumber()).matches() ||
                editCenter.getWorkingTimeFrom() == 0 || editCenter.getWorkingTimeTo() == 0 ||
                editCenter.getCapacity() == 0 || editCenter.getDescription() == null ||
                editCenter.getDescription().equals("")
        );
    }

    public static boolean isGetByIdInvalid(Long centerId) {
        return centerId == null;
    }

    public static boolean isSearchAndSortFreeBloodCentersInvalid(String sortBy, String sortDirection, LocalDateTime dateTime) {
        return (sortBy == null || sortBy.equals("") ||
                sortDirection == null || sortDirection.equals("") ||
                !(sortBy.equals("center") || sortBy.equals("address")) ||
                !(sortDirection.equals("ascending") || sortDirection.equals("descending"))

        );
    }
}
