package com.droid.floatboat.collabcart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.droid.floatboat.collabcart.data.Session;
import com.droid.floatboat.collabcart.models.User;
import com.droid.floatboat.collabcart.net.OnCompleteCallBack;
import com.droid.floatboat.collabcart.utils.CartUtils;

/**
 * @author Pallavi
 */

public class SplashActivity extends Activity {
    private final static String LOG_TAG = SplashActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        EditText email = (EditText) findViewById(R.id.email);
        final String userEmail = email.getText().toString();

        Button loginBtn = (Button) findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!userEmail.equals("leona.workman@gmail.com")  && !userEmail.equals("camacho.nolan@gmail.com")){
                    Toast.makeText(SplashActivity.this, "Invalid Email Address.", Toast.LENGTH_LONG).show();
                    return;
                }

                CartUtils.showProgress(SplashActivity.this);
                new User().setUserEmail("leona.workman@gmail.com").loginUser(new OnCompleteCallBack() { //userEmail
                    @Override
                    public void onComplete(boolean success, int statusCode) {
                        Log.d(LOG_TAG, "Login User success" + success + " statusCode " + statusCode);
                        CartUtils.hideProgress();
                        if (success) {
                            //do something
                            Session.initiateCollabCart();
                            Intent intent = new Intent(SplashActivity.this,ProductCategoryActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(SplashActivity.this, "Invalid Email Address.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
