package com.example.payungteduh;

public class WeatherModel {
	private String mIcon;
	private long mTime;
	private double mTemperature;
	private String mSummary;
	
	public String getIcon() {
		return mIcon;
	}
	public void setIcon(String icon) {
		mIcon = icon;
	}
	public long getTime() {
		return mTime;
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
