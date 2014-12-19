package com.talentum.dubok.weatherworkd.model;

public class WeatherCondition {

    private String description;
    private String temperature;
    private String weatherIcon;

    public String getDescription() {
        return description;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }
}
