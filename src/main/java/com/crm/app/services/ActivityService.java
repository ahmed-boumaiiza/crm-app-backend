package com.crm.app.services;

import com.crm.app.entities.Activity;

import com.crm.app.entities.Contact;
import com.crm.app.repositories.ActivityRepository;
import com.crm.app.repositories.ContactRepository;
import com.crm.app.utils.Constants;

import com.crm.app.utils.Generics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@Slf4j
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private ContactRepository contactRepository;


    public List<Activity> getAllActivities(){
        return activityRepository.findAll();
    }

    public Activity getActivityById(Integer id){
        return activityRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(Constants.ACTIVITY_NOT_FOUND));
    }
//    public Activity createActivity(Activity activity) {
//        log.info("activity added {}",activity);
//        activityRepository.save(activity);
//        return activity;
//    }


        public ResponseEntity<String> createActivity(Map<String,?> activityRequest) {
            try{
            log.info("activity added {}",activityRequest);
            activityRepository.save(getActivityFromMap(activityRequest));
            return Generics.getResponseEntity("Activity added successfully", HttpStatus.CREATED);
            }catch(Exception e){
                e.printStackTrace();
            }
            return Generics.getResponseEntity(Constants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Activity getActivityFromMap(Map<String,?> newActivity) {
        Activity activity = new Activity();
        activity.setDate((String) newActivity.get("date"));
        activity.setActivityType((String) newActivity.get("activityType"));
        activity.setSubject((String) newActivity.get("subject"));
        activity.setNote((String) newActivity.get("note"));
//        List<Contact> list = (ArrayList<Contact>)newActivity.get("participants");
//        Set<Contact> set = new HashSet<>(list);
//        activity.setParticipants(set);
        return activity;
    }


    public Activity updateActivity(Integer id, Map<String, String> newActivityRequest) {
        Activity existingActivity = getActivityById(id);
        return updateActivityFromMap(existingActivity, newActivityRequest);
    }
    public void deleteActivity(Integer id) {
        activityRepository.deleteById(id);
    }

    private Activity updateActivityFromMap(Activity activity,Map<String, String> newActivity) {
        activity.setDate(newActivity.get("date"));
        activity.setActivityType(newActivity.get("activityType"));
        activity.setSubject(newActivity.get("subject"));
        activity.setNote(newActivity.get("note"));
//        activity.setParticipants(newActivity.get("participants"));
        activityRepository.save(activity);
        return activity;
    }

}
