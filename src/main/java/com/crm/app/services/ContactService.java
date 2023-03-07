package com.crm.app.services;

import com.crm.app.entities.Contact;
import com.crm.app.repositories.ContactRepository;
import com.crm.app.utils.Constants;
import com.crm.app.utils.Generics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


@Service
@Slf4j
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;


    public List<Contact> getAllContacts(){
        return contactRepository.findAll();
    }

    public Contact getContactById(Integer id){
        return contactRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(Constants.CONTACT_NOT_FOUND));
    }
    public List<Contact> getAllContactsById(List<Integer> ids){
        return contactRepository.findAllById(ids);

    }

    public Contact createContact(Contact contact) {
            contactRepository.save(contact);
        return contact;
    }



    public Contact updateContactById(Integer id, Contact newContactRequest) {
        Contact existingContact = getContactById(id);
        return updateContact(existingContact, newContactRequest);
    }

    public void deleteContact(Integer id) {
//        contactRepository.deleteContactInJoinTable(id);
        contactRepository.deleteById(id);
    }

    private Contact updateContact(Contact oldcontact,Contact newContact) {
        oldcontact.setFirstName(newContact.getFirstName());
        oldcontact.setLastName(newContact.getLastName());
        oldcontact.setCompany(newContact.getCompany());
        oldcontact.setJob(newContact.getJob());
        oldcontact.setContactOwner(newContact.getContactOwner());
        oldcontact.setEmail(newContact.getEmail());
        oldcontact.setPhone(newContact.getPhone());
        oldcontact.setAddress(newContact.getAddress());
        oldcontact.setCity(newContact.getCity());
        oldcontact.setCountry(newContact.getCountry());
        oldcontact.setState(newContact.getState());
        oldcontact.setZipCode(newContact.getZipCode());
        contactRepository.save(oldcontact);
        return oldcontact;
    }
}
