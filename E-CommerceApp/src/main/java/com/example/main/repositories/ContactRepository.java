package com.example.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.main.models.ContactMessage;

@Repository
public interface ContactRepository extends JpaRepository<ContactMessage, Long> {
	
}