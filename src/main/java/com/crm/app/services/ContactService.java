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

/*
    public ResponseEntity<String> createContact(Map<String, String> contactRequest) {
        try {
            if (validateContactStructure(contactRequest)) {
                Contact contact = contactRepository.findContactByEmail(contactRequest.get("email"));
                if (Objects.isNull(contact)) {
                    contactRepository.save(getContactFromMap(contactRequest));
                    return Generics.getResponseEntity("Contact Added Successfully !", HttpStatus.CREATED);
                } else {
                    return Generics.getResponseEntity("Email already exists", HttpStatus.BAD_REQUEST);
                }

            } else {
                return Generics.getResponseEntity(Constants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Generics.getResponseEntity(Constants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }
*/

    public Contact createContact(Contact contact) {
        contactRepository.save(contact);
        return contact;
    }

    /*
    public ResponseEntity<String> updateContact(Integer id, Map<String, String> newContactRequest) {

        try {
         Contact existingContact = getContactById(id);
          if (validateContactStructure(newContactRequest)) {
            updateContactFromMap(existingContact,newContactRequest);
            return Generics.getResponseEntity("Contact Updated Successfully !", HttpStatus.OK);
        } else {
            return Generics.getResponseEntity(Constants.INVALID_DATA, HttpStatus.BAD_REQUEST);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return Generics.getResponseEntity(Constants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }
  */

    public Contact updateContact(Integer id, Map<String, String> newContactRequest) {
        Contact existingContact = getContactById(id);
        return updateContactFromMap(existingContact, newContactRequest);
    }



    public void deleteContact(Integer id) {
        contactRepository.deleteById(id);
    }
/*
    public Boolean validateContactStructure(Map<String, String> contactRequest){
        if(contactRequest.containsKey("firstName") && contactRequest.containsKey("lastName")
                && contactRequest.containsKey("email")){
            return true;
        }
        return false;
    }

    private Contact getContactFromMap(Map<String,String> contactRequest) {
        Contact contact = new Contact();
        contact.setFirstName(contactRequest.get("firstName"));
        contact.setLastName(contactRequest.get("lastName"));
        contact.setCompany(contactRequest.get("company"));
        contact.setJob(contactRequest.get("job"));
        contact.setEmail(contactRequest.get("email"));
        contact.setPhone(contactRequest.get("phone"));
        contact.setContactOwner(contactRequest.get("contactOwner"));
        contact.setAddress(contactRequest.get("address"));
        contact.setCity(contactRequest.get("city"));
        contact.setCountry(contactRequest.get("country"));
        contact.setState(contactRequest.get("state"));
        contact.setZipCode(contactRequest.get("zipCode"));
        return contact;
    }
    */

    private Contact updateContactFromMap(Contact contact,Map<String,String> contactRequest) {
        contact.setFirstName(contactRequest.get("firstName"));
        contact.setLastName(contactRequest.get("lastName"));
        contact.setCompany(contactRequest.get("company"));
        contact.setJob(contactRequest.get("job"));
        contact.setEmail(contactRequest.get("email"));
        contact.setPhone(contactRequest.get("phone"));
        contact.setContactOwner(contactRequest.get("contactOwner"));
        contact.setAddress(contactRequest.get("address"));
        contact.setCity(contactRequest.get("city"));
        contact.setCountry(contactRequest.get("country"));
        contact.setState(contactRequest.get("state"));
        contact.setZipCode(contactRequest.get("zipCode"));
        contactRepository.save(contact);
        return contact;
    }
/*
    public Page<Contact> getAllContactsPage(Pageable pageable) {
        return contactRepository.findAll(pageable);
    }
    */
}
