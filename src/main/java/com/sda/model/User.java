package com.sda.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class User {
    private String name;
    private String surname;
    private String login;
    private String password;
    private UserRole role;
    private boolean isBlocked;
    private boolean isActive;
}
