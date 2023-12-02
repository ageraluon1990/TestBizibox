package com.example.testbizibox.controller;

import com.example.testbizibox.entity.CallData;
import com.example.testbizibox.exception.BlackPhoneAlreadyExist;
import com.example.testbizibox.exception.PhoneNotFound;
import com.example.testbizibox.service.ServiceBlackList;
import com.example.testbizibox.service.ServiceCallData;
import com.example.testbizibox.service.ServiceContactList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private ServiceCallData serviceCallData;
    @Mock
    private ServiceBlackList serviceBlackList;
    @Mock
    private ServiceContactList serviceContactList;

    @InjectMocks
    private UserController userController;

    @Test
    @DisplayName("Get /allRecords then return HttpStatus.OK")
    void getAllCallByPhone_ReturnValidResponseEntity() throws PhoneNotFound {
        //given
        String phone = "123456789";
        Long duration = 100L;
        List<CallData> callData = new ArrayList<>();
        callData.add(new CallData());
        when(serviceCallData.findAll(phone,duration)).thenReturn(callData);
        //when
        ResponseEntity responseEntity = userController.getAllCallByPhone(phone, duration);
        //then
        verify(serviceCallData, times(1)).findAll(phone,duration);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(callData, responseEntity.getBody());

    }

    @Test
    @DisplayName("Get /allRecords then return HttpStatus.BAD_REQUEST")
    void getAllCallByPhone_ReturnNoValidResponseEntity() throws PhoneNotFound {
        //given
        String phone = "123456789";
        Long duration = 100L;

        when(serviceCallData.findAll(phone,duration)).thenThrow(new PhoneNotFound("no data for phone"));
        //when
        ResponseEntity responseEntity = userController.getAllCallByPhone(phone, duration);
        //then
        verify(serviceCallData, times(1)).findAll(phone,duration);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("no data for phone", responseEntity.getBody());

    }

    @Test
    @DisplayName("PUT /updatePhoneNumber then return nothing")
    void updatePhoneNumber_Test(){
        //given
        String oldPhone = "123";
        String newPhone = "321";

        doNothing().when(serviceCallData) .findByAllPhone(oldPhone,newPhone);
        doNothing().when(serviceBlackList) .findByAllPhone(oldPhone,newPhone);
        doNothing().when(serviceContactList) .findByAllPhone(oldPhone,newPhone);
        //then
        userController.updatePhoneNumber(oldPhone,newPhone);
        //when
        verify(serviceCallData,times(1)).findByAllPhone(oldPhone,newPhone);
        verify(serviceBlackList,times(1)).findByAllPhone(oldPhone,newPhone);
        verify(serviceContactList,times(1)).findByAllPhone(oldPhone,newPhone);
    }

    @Test
    @DisplayName("POST /addCallData then return HttpStatus.OK")
    void addCallData_ReturnValidResponseEntity() throws BlackPhoneAlreadyExist {
        //given
            CallData callData = new CallData();
            doNothing().when(serviceCallData).saveCallData(callData);
        //then
            ResponseEntity responseEntity = userController.addCallData(callData);
        //when
            verify(serviceCallData, times(1)).saveCallData(callData);
            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals("add", responseEntity.getBody());
    }

    @Test
    @DisplayName("POST /addCallData then return BAD_REQUEST")
    void addCallData_ReturnNoValidResponseEntity() throws BlackPhoneAlreadyExist {
        //given
        CallData callData = new CallData();
        doThrow(new BlackPhoneAlreadyExist("this phone on black list")).when(serviceCallData).saveCallData(callData);
        //then
        ResponseEntity responseEntity = userController.addCallData(callData);
        //when
        verify(serviceCallData, times(1)).saveCallData(callData);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("this phone on black list", responseEntity.getBody());
    }


}
