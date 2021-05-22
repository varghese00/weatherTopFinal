package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Station extends Model
{
    public String name;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<Reading>(); //List Reading object is an arraylist called readings
    public double latitude;
    public double longitude;
    public double minTemperature;
    public double maxTemperature;
    public double tempLastValue;
    public int minPressure;
    public int maxPressure;
    public int pressureLastValue;
    public String weatherCode;
    public int beaufort;
    public String windDegree;
    public int minWindSpeed;
    public int maxWindSpeed;
    public double windChill;
    public double windChillValue;
    public int windSpeedLastValue;
    public HashMap<Integer,String> weatherIcons;

    public Station(String name, double latitude,double longitude)
    {
        this.name = name;
        this.latitude = latitude;
        this.longitude=longitude;
    }


}