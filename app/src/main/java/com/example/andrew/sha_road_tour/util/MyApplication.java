package com.example.andrew.sha_road_tour.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

	static Context mContext;
	private static Activity currentActivity;

	@Override
	public void onCreate() {
		super.onCreate();
		mContext = this;

	}
	
	// Activity가 올라올때마다 Activity의 onCreate에서 호출해줘야한다.
	public static void setCurrentActivity(Activity currentActivity) {
		MyApplication.currentActivity = currentActivity;
	}
}
