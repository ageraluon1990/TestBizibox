package com.example.testbizibox.controller;

import com.example.testbizibox.entity.CallData;
import com.example.testbizibox.exception.BlackPhoneAlreadyExist;
import com.example.testbizibox.exception.PhoneNotFound;
import com.example.testbizibox.service.ServiceBlackList;
import com.example.testbizibox.service.ServiceCallData;
import com.example.testbizibox.service.ServiceContactList;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {

    private ServiceCallData serviceCallData;
    private ServiceBlackList serviceBlackList;
    private ServiceContactList serviceContactList;

    @GetMapping("/allRecords")
    public ResponseEntity getAllCallByPhone(@RequestParam(name = "phone") String phone,
                                            @RequestParam(name = "duration") long duration){
        try {
            return ResponseEntity.ok(serviceCallData.findAll(phone,duration));
        }catch (PhoneNotFound e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("dont work service");
        }
    }

    @PutMapping("/updatePhoneNumber")
    public void updatePhoneNumber(
            @RequestParam(name = "oldPhone") String oldPhone,
            @RequestParam(name = "newPhone") String newPhone) {
        serviceCallData.findByAllPhone(oldPhone,newPhone);
        serviceBlackList.findByAllPhone(oldPhone,newPhone);
        serviceContactList.findByAllPhone(oldPhone,newPhone);
    }

    @PostMapping("/addCallData")
    public ResponseEntity addCallData(@RequestBody CallData callData){
        try {
            serviceCallData.saveCallData(callData);
            return ResponseEntity.ok("add");
        }catch (BlackPhoneAlreadyExist e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("dont work service");
        }
    }
}
