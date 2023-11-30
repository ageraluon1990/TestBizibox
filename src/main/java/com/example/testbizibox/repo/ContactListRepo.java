package com.example.testbizibox.repo;

import com.example.testbizibox.entity.ContactList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactListRepo extends JpaRepository <ContactList, Long> {
    List<ContactList> findAllByPhone(String phone);
}
