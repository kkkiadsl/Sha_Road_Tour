package com.example.andrew.sha_road_tour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;

/**
 * Created by andrew on 2016. 10. 18..
 */

public class SplashActivity extends Activity {


    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_splash);
        Handler hand = new Handler();
        hand.postDelayed(new splashhandler(), 1000);
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_CALENDAR);

    }

    private class splashhandler implements Runnable{
        public void run(){
            startActivity(new Intent(getApplication(), MainActivity.class));
            finish();
            return;
        }
    }
    @Override
    public void onBackPressed(){

    }


}
