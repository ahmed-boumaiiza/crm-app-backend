package com.crm.app.services;
import com.crm.app.entities.Activity;
import com.crm.app.entities.Contact;
import com.crm.app.repositories.ActivityRepository;
import com.crm.app.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
@Slf4j
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private ContactService contactService;


    public List<Activity> getAllActivities(){
        return activityRepository.findAll();
    }

    public Activity getActivityById(Integer id){
        return activityRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(Constants.ACTIVITY_NOT_FOUND));
    }

    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }



    public Activity updateActivityById(Integer id, Activity newActivity) {
        Activity existingActivity = getActivityById(id);
        return updateActivity(existingActivity, newActivity);
    }
    public void deleteActivity(Integer id) {
        activityRepository.deleteById(id);
    }

    private Activity updateActivity(Activity oldactivity,Activity newActivity) {
        oldactivity.setDate(newActivity.getDate());
        oldactivity.setActivityType(newActivity.getActivityType());
        oldactivity.setSubject(newActivity.getSubject());
        oldactivity.setNote(newActivity.getNote());
        List<Integer> partIds = new ArrayList<>();
        for (Contact e: newActivity.getParticipants()
             ) {
            partIds.add(e.getId());
        }
        List<Contact> participants = contactService.getAllContactsById(partIds);
        oldactivity.setParticipants(participants);
        activityRepository.save(oldactivity);
        return oldactivity;
    }

}
