package com.example.testbizibox.repo;

import com.example.testbizibox.entity.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BlackListRepo extends JpaRepository <BlackList, Long> {
    List<BlackList> findAllByPhone(String phone);
}
