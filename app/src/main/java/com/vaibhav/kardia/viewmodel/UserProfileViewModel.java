package com.vaibhav.kardia.viewmodel;

import com.vaibhav.kardia.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserProfileViewModel extends ViewModel {
    public MutableLiveData<String> firstName = new MutableLiveData<>();
    public MutableLiveData<String> lastName = new MutableLiveData<>();
    public MutableLiveData<String> dob = new MutableLiveData<>();

    private UserProfileContract userProfileContract;

    private MutableLiveData<User> userLiveData;

    public MutableLiveData<User> getUser() {
        if (userLiveData == null) {
            userLiveData = new MutableLiveData<>();
        }
        return userLiveData;
    }

    public void setUserProfileContract(UserProfileContract data) {
        this.userProfileContract = data;
    }

    public void onNext() {
        User user = new User(firstName.getValue(), lastName.getValue(), dob.getValue());
        userLiveData.setValue(user);
        userProfileContract.performAction(user);
    }

    public String calculateAge(MutableLiveData<String> dob) {
        String data = dob.getValue();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = formatter.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Instant instant = date.toInstant();
        ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
        LocalDate givenDate = zone.toLocalDate();
        Period period = Period.between(givenDate, LocalDate.now());
        System.out.print(period.getYears() + " years " + period.getMonths() + " and " + period.getDays() + " days");
        return period.getYears() + " years , " + period.getMonths() + " month, " + period.getDays() + " days";
    }

    public interface UserProfileContract {
        void performAction(User user);
    }
}