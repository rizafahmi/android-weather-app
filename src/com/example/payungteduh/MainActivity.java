package com.example.payungteduh;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

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
	private CurrentWeather mCurrentWeather = new CurrentWeather();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if (isNetworkIsAvailable()) {
			double lat = -7.01;
			double lng = 108.77;
			String API_KEY = "be8ef7ce626a197a46bb0c9e542bc746";
			
			String forecastUrl = "https://api.forecast.io/forecast/" + API_KEY + "/" + lat + "," + lng;
			
			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder().url(forecastUrl).build();
			
			Call call = client.newCall(request);
			call.enqueue(new Callback() {
				
				@Override
				public void onResponse(Call arg0, Response response) throws IOException {
					try {
						String jsonData = response.body().string();
						Log.v(TAG, jsonData);
						if (response.isSuccessful()) {
							mCurrentWeather = getCurrentDetails(jsonData);
						} else {
							alertUserAboutError();
						}
					} catch (IOException e) {
						Log.e(TAG, "Exception caught: ", e);
					} catch (JSONException e) {
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
		
	protected CurrentWeather getCurrentDetails(String jsonData) throws JSONException {
		JSONObject forecast = new JSONObject(jsonData);
		
		String timezone = forecast.getString("timezone");
		Log.i(TAG, "From JSON: " + timezone);
		
		JSONObject currently = forecast.getJSONObject("currently");
		
		CurrentWeather currentWeather = new CurrentWeather();
		currentWeather.setSummary(currently.getString("summary"));
		currentWeather.setIcon(currently.getString("icon"));
		currentWeather.setTime(currently.getLong("time"));
		currentWeather.setTemperature(currently.getDouble("temperature"));
		
		return currentWeather;
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
