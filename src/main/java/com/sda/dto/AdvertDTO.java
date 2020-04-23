package com.sda.dto;

import com.sda.model.Advert;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AdvertDTO {
    private Advert advert;
    private boolean isObserved;
}
