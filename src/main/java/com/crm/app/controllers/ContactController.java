package com.crm.app.controllers;

import com.crm.app.entities.Contact;
import com.crm.app.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@Validated
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
  public ResponseEntity<Contact> createContact(@Valid @RequestBody Contact contact) {
        Contact newContact = contactService.createContact(contact);
        return new ResponseEntity<>(newContact,HttpStatus.CREATED);
    }

    /*
    @PostMapping("/create")
    public ResponseEntity<String> createContact(@Valid @RequestBody Map<String, String> contact) {
        return contactService.createContact(contact);
    }
*/

@PutMapping(path="/update/{id}")
    public ResponseEntity<Contact> updateContact(
            @Valid
            @PathVariable("id") Integer id,
            @RequestBody Map<String, String> newContact) {
        Contact updatedContact = contactService.updateContact(id,newContact);
        return new ResponseEntity<>(updatedContact,HttpStatus.OK);
    }

/*
    @PutMapping(path="/update/{id}")
    public ResponseEntity<String> updateContact(
            @Valid
            @PathVariable("id") Integer id,
            @RequestBody Map<String, String> newContact) {
        return contactService.updateContact(id,newContact);
    }
*/
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteContact(
            @PathVariable("id") Integer id){
        contactService.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
