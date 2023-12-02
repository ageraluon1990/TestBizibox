package com.example.testbizibox.service;

import com.example.testbizibox.entity.BlackList;
import com.example.testbizibox.repo.BlackListRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceBlackListTest {
    @Mock
    private BlackListRepo blackListRepo;
    @InjectMocks
    private ServiceBlackList serviceBlackList;

    @Test
    @DisplayName("ServiceBlackList findByAllPhone")
    void findByAllPhone_Test(){
        //given
        String oldPhone = "123";
        String newPhone = "321";
        List<BlackList> blackLists = Arrays.asList(new BlackList());
        when(blackListRepo.findAllByPhone(oldPhone)).thenReturn(blackLists);

        //then
        serviceBlackList.findByAllPhone(oldPhone,newPhone);
        //when

        verify(blackListRepo, times(1)).findAllByPhone(oldPhone);
    }


}
