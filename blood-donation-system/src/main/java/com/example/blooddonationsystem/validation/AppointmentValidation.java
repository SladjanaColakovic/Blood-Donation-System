package com.example.blooddonationsystem.validation;

import com.example.blooddonationsystem.dto.AppointmentDTO;

public class AppointmentValidation {
    public static boolean isNewAppointmentInvalid(AppointmentDTO newAppointment) {
        return (newAppointment.getCenterId() == null ||
                newAppointment.getDonorUsername() == null ||
                newAppointment.getDonorUsername().equals("") ||
                newAppointment.getStartDateTime() == null
        );
    }

    public static boolean isGetAppointmentsInvalid(String username) {
        return (username == null || username.equals(""));
    }

    public static boolean isCancelInvalid(Long id) {
        return id == null;
    }

    public static boolean isSearchAndSortDonorAppointmentsInvalid(String sortBy, String sortDirection, String donorUsername) {
        return (donorUsername == null || donorUsername.equals("") ||
                sortBy == null || sortBy.equals("") ||
                sortDirection == null || sortDirection.equals("") ||
                !(sortBy.equals("center") || sortBy.equals("address") || sortBy.equals("date")) ||
                !(sortDirection.equals("ascending") || sortDirection.equals("descending"))
        );
    }

}
