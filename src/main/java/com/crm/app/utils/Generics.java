package com.crm.app.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Generics {
    public Generics() {
    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus) {
        return new ResponseEntity<String> ("{\"message\":\"" +responseMessage+ "\"}",  httpStatus);
    }
}
