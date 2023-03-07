package com.crm.app.controllers;

import com.crm.app.entities.Contact;
import com.crm.app.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "api/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping("/getall")
    public ResponseEntity<List<Contact>> getAllContacts(){
        List<Contact> contacts =  contactService.getAllContacts();
        return new ResponseEntity<>(contacts,HttpStatus.OK);
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable("id") Integer id){
        Contact contact =  contactService.getContactById(id);
        return new ResponseEntity<>(contact,HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        Contact newContact = contactService.createContact(contact);
        return new ResponseEntity<>(newContact,HttpStatus.CREATED);
    }

@PutMapping(path="/update/{id}")
    public ResponseEntity<Contact> updateContact(
            @PathVariable("id") Integer id,
            @RequestBody Contact newContact) {
        Contact updatedContact = contactService.updateContactById(id,newContact);
        return new ResponseEntity<>(updatedContact,HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteContact(
            @PathVariable("id") Integer id){
        contactService.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
