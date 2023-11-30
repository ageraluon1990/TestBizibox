package com.example.testbizibox.service;

import com.example.testbizibox.entity.BlackList;
import com.example.testbizibox.repo.BlackListRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceBlackList {

    private BlackListRepo blackListRepo;

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
