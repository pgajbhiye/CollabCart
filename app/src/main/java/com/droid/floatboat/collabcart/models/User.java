package com.droid.floatboat.collabcart.models;

import com.droid.floatboat.collabcart.net.OnCompleteCallBack;
import com.droid.floatboat.collabcart.services.UserDetailsService;

public class User{

    private String userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public User setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public void loginUser(OnCompleteCallBack onComplete){
        UserDetailsService.loginUser(this, onComplete); //userEmail

    }
}