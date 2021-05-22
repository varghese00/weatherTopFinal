package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Reading extends Model {
    public String date;
    public String code;
    public double temperature;
    public int windSpeed;
    public double windDirection;
    public int pressure;


    public Reading(String date, String code, double temperature, int windSpeed, double windDirection, int pressure) {
        this.date = date;
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.pressure = pressure;

    }
}



