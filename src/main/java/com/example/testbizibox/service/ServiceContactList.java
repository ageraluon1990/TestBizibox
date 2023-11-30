package com.example.testbizibox.service;

import com.example.testbizibox.entity.BlackList;
import com.example.testbizibox.entity.ContactList;
import com.example.testbizibox.repo.ContactListRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceContactList {
    private ContactListRepo contactListRepo;

    public void findByAllPhone(String oldPhone, String newPhone){
        List<ContactList> data = contactListRepo.findAllByPhone(oldPhone);
        if (!data.isEmpty()){
            data.forEach(el -> {
                el.setPhone(newPhone);
                contactListRepo.save(el);
            });
        }
    }
    public List<ContactList> findPhone(String phone){
        List<ContactList> data = contactListRepo.findAllByPhone(phone);
        return data;
    }

}