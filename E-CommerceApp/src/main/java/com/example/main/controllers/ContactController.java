package com.example.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.main.models.ContactMessage;
import com.example.main.repositories.ContactRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @PostMapping("/submit")
    public String submitMessage(@RequestBody ContactMessage msg) {
        msg.setSubmittedAt(LocalDateTime.now());
        contactRepository.save(msg);
        return "Message received successfully! We will contact you soon.";
    }

    @GetMapping("/all") // This makes the final URL: http://localhost:8080/api/contact/all
    public List<ContactMessage> getAllMessages() {
        return contactRepository.findAll();
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long id) {
        return contactRepository.findById(id)
            .map(message -> {
                contactRepository.delete(message); 
                return ResponseEntity.ok().build();
            })
            .orElse(ResponseEntity.notFound().build());
    }
    
}