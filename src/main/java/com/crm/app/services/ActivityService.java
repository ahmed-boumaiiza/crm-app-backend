package com.crm.app.services;

import com.crm.app.entities.Activity;

import com.crm.app.repositories.ActivityRepository;
import com.crm.app.utils.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;


    public List<Activity> getAllActivities(){
        return activityRepository.findAll();
    }

    public Activity getActivityById(Integer id){
        return activityRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(Constants.ACTIVITY_NOT_FOUND));
    }
    public Activity createActivity(Activity activity) {
        activityRepository.save(activity);
        return activity;
    }
    public Activity updateActivity(Integer id, Activity newActivityRequest) {
        Activity existingActivity = getActivityById(id);
        return updateActivityFromMap(existingActivity, newActivityRequest);
    }
    public void deleteActivity(Integer id) {
        activityRepository.deleteById(id);
    }

    private Activity updateActivityFromMap(Activity activity,Activity newActivity) {
        activity.setDate(newActivity.getDate());
        activity.setActivityType(newActivity.getActivityType());
        activity.setSubject(newActivity.getSubject());
        activity.setNote(newActivity.getSubject());
        //activity.setParticipants(newActivity.getParticipants());
        activityRepository.save(activity);
        return activity;
    }

}
