package com.davidbonelo.utils;

import com.davidbonelo.models.User;
import com.davidbonelo.models.UserRole;

import java.util.ResourceBundle;

public class Permissions {

    private Permissions() {
        throw new IllegalStateException("Utility Class");
    }

    public static boolean validMenuAccess(User user, UserRole requiredRole) {
        boolean valid = validPermission(user, requiredRole);
        if (!valid) {
            System.out.println(ResourceBundle.getBundle("messages").getString("unknownOption"));
        }
        return valid;
    }

    public static boolean validPermission(User user, UserRole requiredRole) {
        if (user == null) return false;
        if (user.getRole().equals(UserRole.SUPER)) {
            return true;
        }
        if (user.getRole().equals(UserRole.ADMINISTRATOR) && ((requiredRole.equals(UserRole.READER)) || (requiredRole.equals(UserRole.EMPLOYEE)))) {
            return true;
        }
        if (user.getRole().equals(UserRole.EMPLOYEE) && requiredRole.equals(UserRole.READER)) {
            return true;
        }
        return user.getRole().equals(requiredRole);
    }
}
