package com.sda.service;

import com.sda.dto.AdvertDTO;
import com.sda.model.Advert;
import com.sda.respository.AdvertRepository;
import com.sda.respository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ObserveService {

    private static ObserveService observeService;
    private UserRepository userRepository;
    private AdvertRepository advertRepository;
    private AdvertService advertService;

    public static ObserveService getInstance() {
        if (observeService == null) {
            observeService = new ObserveService(
                    UserRepository.aUserRepository(),
                    AdvertRepository.aAdvertRepository(),
                    AdvertService.aAdvertService()
            );
        }
        return observeService;
    }


    public void observeAdvert(Long userId, Long advertId) {
        userRepository.updateAddObserved(userId, advertId);
    }

    public void notObserveAdvert(Long userId, Long advertId) {
        userRepository.updateRemoveObserved(userId, advertId);
    }

    public List<AdvertDTO> getUserObservedAdvertDTOs(Long id) {
        List<AdvertDTO> advertDTOs = new ArrayList<>();
        getUserObservedAdverts(id).forEach(advert -> advertDTOs.add(new AdvertDTO(advert, false)));
        return advertDTOs;
    }

    public List<Advert> getUserObservedAdverts(Long id) {
        return advertRepository.findObservedByUserId(id);
    }

    public boolean isObservedByUser(Long advertId, Long userId) {
        final List<Advert> observedByUser = advertRepository.findObservedByUserId(userId);
        final Optional<Advert> advertOptional = observedByUser.stream()
                .filter(advert -> advert.getId().equals(advertId))
                .findAny();
        return advertOptional.isPresent();
    }

    public List<AdvertDTO> markObservedByUser(List<AdvertDTO> advertDTOs, Long userId) {
        advertDTOs.forEach(advertDTO -> {
            if (isObservedByUser(advertDTO.getAdvert().getId(), userId)) {
                advertDTO.setObserved(true);
            }
        });
        return advertDTOs;
    }
}
