package com.sda.service;

import com.sda.model.Advert;
import com.sda.respository.AdvertRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdvertService {
    private static AdvertService advertService;
    private AdvertRepository advertRepository;

    public static AdvertService aAdvertService(){
        if(advertService == null){
            advertService = new AdvertService(AdvertRepository.aAdvertRepository());
        }
        return advertService;
    }

    public boolean postAdvert(Advert advertisement) {
        if(advertisement != null){
            return advertRepository.save(advertisement);
        }
        return false;
    }

    public List<Advert> getAdverts() {
        return advertRepository.findAll();
    }

    public List<Advert> getUserAdverts(String login) {
        return advertRepository.findAdvertsByLogin(login);
    }
}
