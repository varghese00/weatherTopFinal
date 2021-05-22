package controllers;

import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;
import utils.StationAnalytics;

import java.util.List;

public class StationCtrl extends Controller {
    public static void index(Long id) {
        Station station = Station.findById(id);
        Logger.info("Station id = " + id);
        station.weatherCode=StationAnalytics.weatherCondition(station.readings);
        station.minTemperature = StationAnalytics.minTemp(station.readings);
        station.maxTemperature = StationAnalytics.maxTemp(station.readings);
        station.tempLastValue=StationAnalytics.getTempTrend(station.readings);
        station.maxPressure = StationAnalytics.maximumPressure(station.readings);
        station.minPressure = StationAnalytics.minimumPressure(station.readings);
        station.pressureLastValue=StationAnalytics.getPressureTrend(station.readings);
        station.beaufort=StationAnalytics.beaufortScale(station.readings);
        station.maxWindSpeed = StationAnalytics.maximumWindSpeed(station.readings);
        station.minWindSpeed = StationAnalytics.minimumWindSpeed(station.readings);
        station.windSpeedLastValue=StationAnalytics.getWindSpeedTrend(station.readings);
        station.windDegree=StationAnalytics.windCompassDegree(station.readings);
        station.windChillValue=StationAnalytics.windChillMethod(station.readings);
        render("station.html", station);
    }

    public static void deleteReading(Long id, Long readingid) {
        Station station = Station.findById(id);
        Reading reading = Reading.findById(readingid);
        Logger.info("Removing" + reading.code);
        station.readings.remove(reading);
        station.save();
        reading.delete();
        redirect("/stations/" + id);
        render("station.html", station);
    }

    public static void addReading(Long id, String date, String code, double temperature, int windSpeed, double windDirection, int pressure) {
        Reading reading = new Reading(date, code, temperature, windSpeed, windDirection, pressure);
        Station station = Station.findById(id);//associate each station with each above reading
        station.readings.add(reading);
        station.save();
        redirect("/stations/" + id);
    }

}