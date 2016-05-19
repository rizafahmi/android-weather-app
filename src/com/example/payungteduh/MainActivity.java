package com.example.payungteduh;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends Activity {
	
	private final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if (isNetworkIsAvailable()) {
		
			String forecastUrl = "http://api.openweathermap.org/data/2.5/weather?q=Ciledug&APPID=b15b9835567a918fbec1d1c6e67f347a";
			
			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder().url(forecastUrl).build();
			
			Call call = client.newCall(request);
			call.enqueue(new Callback() {
				
				@Override
				public void onResponse(Call arg0, Response response) throws IOException {
					try {
						Log.v(TAG, response.body().string());
						if (response.isSuccessful()) {
							
						} else {
							alertUserAboutError();
						}
					} catch (IOException e) {
						Log.e(TAG, "Exception caught: ", e);
					}
					
				}
				
				@Override
				public void onFailure(Call arg0, IOException arg1) {
	
					
				}
			});
			
		} else {
			Toast.makeText(this, "Sorry, the network is unavailable", Toast.LENGTH_LONG).show();
		}
	}
		
	private boolean isNetworkIsAvailable() {
		ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mNetworkInfo = manager.getActiveNetworkInfo();
		boolean isAvailable = false;
		
		if (mNetworkInfo != null && mNetworkInfo.isConnected()) {
			isAvailable = true;
		}
		return isAvailable;
	}

	protected void alertUserAboutError() {
		
		AlertDialogFragment alertDialog = new AlertDialogFragment();
		alertDialog.show(getFragmentManager(), "Error");
		
	}
}
