package com.example.payungteduh;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import android.text.format.DateFormat;

public class CurrentWeather {
	private String mIcon;
	private long mTime;
	private double mTemperature;
	private String mSummary;
	private String mTimezone;
	
	public String getTimezone() {
		return mTimezone;
	}
	public void setTimezone(String timezone) {
		mTimezone = timezone;
	}
	public String getIcon() {
		return mIcon;
	}
	public void setIcon(String icon) {
		mIcon = icon;
	}
	public long getTime() {
		return mTime;
	}
	
	public String getFormattedTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("H:mm");
		formatter.setTimeZone(TimeZone.getTimeZone(getTimezone()));
		Date dateTime = new Date(getTime() * 1000);
		return formatter.format(dateTime);
	}
	
	public void setTime(long time) {
		mTime = time;
	}
	public double getTemperature() {
		return mTemperature;
	}
	public void setTemperature(double temperature) {
		mTemperature = temperature;
	}
	public String getSummary() {
		return mSummary;
	}
	public void setSummary(String summary) {
		mSummary = summary;
	}
	
	
}
