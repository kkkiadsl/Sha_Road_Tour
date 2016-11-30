package com.example.andrew.sha_road_tour.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.widget.Toast;

import com.example.andrew.sha_road_tour.CallResultActivity;
import com.example.andrew.sha_road_tour.R;

/**
 * Created by andrew on 2016. 10. 24..
 */
public class AlarmReceive extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "Alarm Received!", Toast.LENGTH_LONG).show();

        Intent intent2 = new Intent(context, CallResultActivity.class);
        PendingIntent pender = PendingIntent.getActivity(context, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(context);

        boolean isEntering = intent.getBooleanExtra(LocationManager.KEY_PROXIMITY_ENTERING, false);
        if(isEntering) {
            // 작은 아이콘 이미지.
            builder.setSmallIcon(R.drawable.ic_action_map);

            // 알림이 출력될 때 상단에 나오는 문구.
            builder.setTicker("위치확인 서비스");

            // 알림 출력 시간.
            builder.setWhen(System.currentTimeMillis());

            // 알림 제목.
            builder.setContentTitle("Check of area");

            // 프로그래스 바.
            builder.setProgress(100, 100, false);

            // 알림 내용.
            builder.setContentText("여기는 무안입니다!");
        } else{
            // 작은 아이콘 이미지.
            builder.setSmallIcon(R.drawable.ic_action_map);

            // 알림이 출력될 때 상단에 나오는 문구.
            builder.setTicker("위치확인 서비스");

            // 알림 출력 시간.
            builder.setWhen(System.currentTimeMillis());

            // 알림 제목.
            builder.setContentTitle("Check for area");

            // 프로그래스 바.
            builder.setProgress(100, 0, false);

            // 알림 내용.
            builder.setContentText("여기는 어디?");
        }

        // 알림시 사운드, 진동, 불빛을 설정 가능.
        builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS);

        // 알림 터치시 반응.
        builder.setContentIntent(pender);

        // 알림 터치시 반응 후 알림 삭제 여부.
        builder.setAutoCancel(true);

        // 우선순위.
        builder.setPriority(Notification.PRIORITY_MAX);


        // 고유ID로 알림을 생성.
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(123456, builder.build());
    }



}