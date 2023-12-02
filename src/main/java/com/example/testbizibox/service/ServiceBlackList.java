package com.example.testbizibox.service;

import com.example.testbizibox.entity.BlackList;
import com.example.testbizibox.repo.BlackListRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceBlackList {

    private final BlackListRepo blackListRepo;

    public void findByAllPhone(String oldPhone, String newPhone){
        List<BlackList> data = blackListRepo.findAllByPhone(oldPhone);
        if (!data.isEmpty()){
            data.forEach(el -> {
                el.setPhone(newPhone);
                blackListRepo.save(el);
            });
        }
    }
    public List<BlackList> findPhone(String phone){
        List<BlackList> data = blackListRepo.findAllByPhone(phone);
        return data;
    }

}
