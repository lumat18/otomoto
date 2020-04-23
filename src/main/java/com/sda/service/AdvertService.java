package com.sda.service;

import com.sda.model.Advert;
import com.sda.respository.AdvertRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

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

    public boolean saveAdvert(Advert advertisement) {
        if(advertisement != null){
            return advertRepository.save(advertisement);
        }
        return false;
    }

    public List<Advert> getAdverts() {
        return advertRepository.findAll();
    }

    public List<Advert> getUserAdverts(Long userId) {
        return advertRepository.findAdvertsByLogin(userId);
    }

    public Optional<Advert> getAdvertById(String advertId) {
        return advertRepository.findAdvertById(advertId);
    }
}
