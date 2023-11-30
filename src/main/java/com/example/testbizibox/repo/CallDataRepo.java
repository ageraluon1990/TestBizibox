package com.example.testbizibox.repo;

import com.example.testbizibox.entity.CallData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CallDataRepo extends JpaRepository<CallData, Long> {
        List<CallData> findAllByPhoneNumberAndDurationGreaterThan(String phoneNumber, Long duration);

        List<CallData> findAllByPhoneNumber(String phoneNumber);
}
