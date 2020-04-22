package com.sda.service;

import com.sda.respository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ObserveService {

    private static ObserveService observeService;
    private UserRepository userRepository;

    public static ObserveService getInstance() {
        if (observeService == null) {
            observeService = new ObserveService(
                    UserRepository.aUserRepository());
        }
        return observeService;
    }


    public void observeAdvert(Long userId, Long advertId) {
        userRepository.updateObserved(userId, advertId);
    }
}
