package com.crm.app.controllers;

import com.crm.app.entities.Activity;
import com.crm.app.services.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path="api/activity")
@Slf4j
public class ActivityController {
    @Autowired
    ActivityService activityService;

    @GetMapping("/getall")
    public ResponseEntity<List<Activity>> getAllActivities(){
        List<Activity> activities =  activityService.getAllActivities();
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable("id") Integer id){
        Activity activity =  activityService.getActivityById(id);
        return new ResponseEntity<>(activity,HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) {
        Activity newActivity = activityService.createActivity(activity);
        return new ResponseEntity<>(newActivity,HttpStatus.CREATED);
    }


    @PutMapping(path="/update/{id}")
    public ResponseEntity<Activity> updateActivity(
            @PathVariable("id") Integer id,
            @RequestBody Activity newActivity) {
        Activity updatedActivity = activityService.updateActivityById(id,newActivity);
        return new ResponseEntity<>(updatedActivity,HttpStatus.OK);
    }


    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteActivity(
            @PathVariable("id") Integer id){
        activityService.deleteActivity(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
