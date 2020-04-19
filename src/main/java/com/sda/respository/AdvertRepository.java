package com.sda.respository;

import com.sda.model.Advert;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdvertRepository {
    private static AdvertRepository advertRepository;

    private List<Advert> adverts;

    public static AdvertRepository aAdvertRepository(){
        if(advertRepository == null){
            advertRepository = new AdvertRepository(new ArrayList<>());
        }
        return advertRepository;
    }

    public boolean save(Advert advert){
        if(advert!=null){
            adverts.add(advert);
            System.out.println("advertisements = " + adverts);
            return true;
        }
        return false;
    }

    public List<Advert> findAll(){
        return List.copyOf(adverts);
    }

    public List<Advert> findAdvertsByLogin(String login){
        return adverts.stream()
                .filter(u -> u.getUser().getLogin().equals(login))
                .collect(Collectors.toList());
    }
}
