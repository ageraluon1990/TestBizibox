package com.example.testbizibox.service;

import com.example.testbizibox.entity.BlackList;
import com.example.testbizibox.entity.CallData;
import com.example.testbizibox.entity.ContactList;
import com.example.testbizibox.exception.BlackPhoneAlreadyExist;
import com.example.testbizibox.exception.PhoneNotFound;
import com.example.testbizibox.repo.CallDataRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ServiceCallData {
  private CallDataRepo callDataRepo;
  private ServiceBlackList serviceBlackList;
  private ServiceContactList serviceContactList;

  public List<CallData> findAll(String phoneNumber, Long duration) throws PhoneNotFound {

    List<CallData> data =  callDataRepo.findAllByPhoneNumberAndDurationGreaterThan(phoneNumber,duration);
    if (!data.isEmpty()){
        return data;
    } else {
      throw new PhoneNotFound("no data for phone");
    }
  }

  public void findByAllPhone(String oldPhone, String newPhone){
    List<CallData> data = callDataRepo.findAllByPhoneNumber(oldPhone);
    if (!data.isEmpty()){
      data.forEach(el -> {
        el.setPhoneNumber(newPhone);
        callDataRepo.save(el);
      });
    }
  }
  public void saveCallData(CallData callData) throws BlackPhoneAlreadyExist {

    String phone = callData.getPhoneNumber();
    List<BlackList> data = serviceBlackList.findPhone(phone);
    if (!data.isEmpty()){
      throw new BlackPhoneAlreadyExist("this phone on black list");
    }
    List<ContactList> contactLists = serviceContactList.findPhone(phone);
    if (contactLists.isEmpty()){
      callData.setSaveContact(true);
    } else {
      callData.setSaveContact(false);
    }
    callDataRepo.save(callData);
  }
}

//