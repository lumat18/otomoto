package com.sda.service;

import com.sda.exception.InvalidInputDataException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InputValidatingService {
    private static InputValidatingService inputValidatingService;

    public static InputValidatingService aInputValidatingService(){
        if(inputValidatingService == null){
            inputValidatingService = new InputValidatingService();
        }
        return inputValidatingService;
    }

    public Integer validateNumberField(String field) throws InvalidInputDataException {
        if(field.matches("[0-9]+")){
            return Integer.parseInt(field);
        }
        if (field.isEmpty()) return 0;
        throw new InvalidInputDataException();
    }

    public String getCorrectAdvertContent(String input){
        if(!input.isEmpty()){
            return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
        }
        return "";
    }
}
