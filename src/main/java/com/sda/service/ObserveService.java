package com.sda.service;

import com.sda.model.Advert;
import com.sda.respository.AdvertRepository;
import com.sda.respository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ObserveService {

    private static ObserveService observeService;
    private UserRepository userRepository;
    private AdvertRepository advertRepository;

    public static ObserveService getInstance() {
        if (observeService == null) {
            observeService = new ObserveService(
                    UserRepository.aUserRepository(),
                    AdvertRepository.aAdvertRepository());
        }
        return observeService;
    }


    public void observeAdvert(Long userId, Long advertId) {
        userRepository.updateObserved(userId, advertId);
    }

    public List<Advert> getUserObservedAdverts(Long id) {
        return advertRepository.findObservedByUserId(id);
    }
}
