package com.sda.request;

import lombok.Value;

@Value
public class EditUserRequest {
    private String name;
    private String surname;
    private String login;
}
