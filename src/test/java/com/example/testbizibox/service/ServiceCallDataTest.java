package com.example.testbizibox.service;

import com.example.testbizibox.entity.BlackList;
import com.example.testbizibox.entity.CallData;
import com.example.testbizibox.exception.BlackPhoneAlreadyExist;
import com.example.testbizibox.exception.PhoneNotFound;
import com.example.testbizibox.repo.CallDataRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceCallDataTest {
    @Mock
    private CallDataRepo callDataRepo;
    @Mock
    private ServiceBlackList serviceBlackList;
    @Mock
    private ServiceContactList serviceContactList;
    @InjectMocks
    private ServiceCallData serviceCallData;
    @Test
    void findAll_ReturnListWithOutExceptions() throws PhoneNotFound {
        String phoneNumber = "123";
        Long duration = 100L;
        List<CallData> callDataList = Arrays.asList(new CallData());
        when(callDataRepo.findAllByPhoneNumberAndDurationGreaterThan(phoneNumber,duration)).thenReturn(callDataList);

        List<CallData> dataList = serviceCallData.findAll(phoneNumber, duration);

        verify(callDataRepo, times(1)).findAllByPhoneNumberAndDurationGreaterThan(phoneNumber, duration);
        assertEquals(dataList, callDataList);
    }

    @Test
    void findAll_ReturnListWithExceptions(){
        String phoneNumber = "123";
        Long duration = 100L;
        when(callDataRepo.findAllByPhoneNumberAndDurationGreaterThan(phoneNumber,duration)).thenReturn(Collections.emptyList());

        assertThrows(PhoneNotFound.class, () -> serviceCallData.findAll(phoneNumber, duration));
        verify(callDataRepo, times(1)).findAllByPhoneNumberAndDurationGreaterThan(phoneNumber, duration);
    }

    @Test
    void findAllPhone_Test(){
        String oldPhone = "123";
        String newPhone = "321";
        List<CallData> callDataList = Arrays.asList(new CallData());
        when(callDataRepo.findAllByPhoneNumber(oldPhone)).thenReturn(callDataList);

        serviceCallData.findByAllPhone(oldPhone,newPhone);

        verify(callDataRepo, times(1)).findAllByPhoneNumber(oldPhone);
    }

    @Test
    void saveCallData_WithOutExceptions(){
        CallData callData = new CallData();
        String phone = callData.getPhoneNumber();
        when(serviceBlackList.findPhone(phone)).thenReturn(Collections.emptyList());

        assertDoesNotThrow(() -> serviceCallData.saveCallData(callData));
        verify(serviceBlackList, times(1)).findPhone(phone);
        verify(callDataRepo, times(1)).save(callData);
        verify(serviceContactList, times(1)).findPhone(phone);
    }

    @Test
    void saveCallData_WithExceptions(){
        CallData callData = new CallData();
        String phone = callData.getPhoneNumber();
        List<BlackList> blackLists = Arrays.asList(new BlackList());
        when(serviceBlackList.findPhone(phone)).thenReturn(blackLists);

        assertThrows(BlackPhoneAlreadyExist.class, () -> serviceCallData.saveCallData(callData));
        verify(serviceBlackList, times(1)).findPhone(phone);
        verify(serviceContactList, times(1)).findPhone(phone);
    }
}