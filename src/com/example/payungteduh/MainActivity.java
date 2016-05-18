package com.example.payungteduh;

import java.io.IOException;

import android.app.Activity;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends Activity {
	
	private final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String forecastUrl = "http://api.openweathermap.org/data/2.5/weather?q=Ciledug&APPID=b15b9835567a918fbec1d1c6e67f347a";
		
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(forecastUrl).build();
		Call call = client.newCall(request);
		
		try {
			Response response = call.execute();
			if (response.isSuccessful()) {
				Log.v(TAG, response.body().string());
			}
		} catch (IOException e) {
			Log.e(TAG, "Exception caught: ", e);
		}
	}
}
