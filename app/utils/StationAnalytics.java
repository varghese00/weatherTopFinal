package utils;

import com.mchange.v2.cfg.PropertiesConfigSource;
import models.Reading;

import java.util.HashMap;
import java.util.List;

import static java.lang.Object.*;

public class StationAnalytics {

    //Weather Condition Methods

    public static String weatherCondition(List<Reading> readings) {
        Reading weatherCode = null;
        int n = readings.size();
        if (n > 0) {
            if (n == 1) {
                weatherCode = readings.get(0);
            } else {
                weatherCode = readings.get(n - 1);
            }

            switch (weatherCode.code) {
                case "100":
                    return "Clear";
                case "200":
                    return "Partial Clouds";
                case "300":
                    return "Cloudy";
                case "400":
                    return "Light Showers";
                case "500":
                    return "Heavy Showers";
                case "600":
                    return "Rain";
                case "700":
                    return "Snow";
                case "800":
                    return "Thunder";
            }
        }
        return null;
    }

//    public static String weatherIcon(int code) {
//        HashMap<Integer, String> weatherIcons;
//        weatherIcons = new HashMap<Integer, String>();
//        weatherIcons.put(100, "sun icon");
//        weatherIcons.put(200, "cloud sun icon");
//        weatherIcons.put(300, "cloud icon");
//        weatherIcons.put(400, "cloud sun rain icon");
//        weatherIcons.put(500, "cloud showers heavy icon");
//        weatherIcons.put(600, "cloud rain icon");
//        weatherIcons.put(700, "snowflake icon");
//        weatherIcons.put(800, "bolt icon");
//
//        return weatherIcons.get(code);
//    }

    //Temperature methods

    public static double minTemp(List<Reading> readings) {
        Reading minTemperature = null;
        if (readings.size() > 0) {
            minTemperature = readings.get(0);
            for (Reading reading : readings) {
                if (reading.temperature < minTemperature.temperature) {
                    minTemperature = reading;
                }
            }
        }
        return minTemperature != null ? minTemperature.temperature : 0;
    }

    public static double maxTemp(List<Reading> readings) {
        Reading maxTemperature = null;
        if (readings.size() > 0) {
            maxTemperature = readings.get(0);
            for (Reading reading : readings) {
                if (reading.temperature > maxTemperature.temperature) {
                    maxTemperature = reading;
                }
            }
        }
        return maxTemperature != null ? maxTemperature.temperature : 0;
    }

    public static double getTempTrend(List<Reading> readings) {
        Reading tempSecondLastValue = null;
        Reading tempLastValue = null;
        int n = readings.size();
        if (n > 0) {
            if (n == 1) {
                tempLastValue = readings.get(0);
                tempSecondLastValue = readings.get(0);
            } else {
                tempLastValue = readings.get(n - 1);
                tempSecondLastValue = readings.get(n - 2);
            }

            if (tempSecondLastValue.temperature < tempLastValue.temperature) {
                return 1;
            } else if (tempSecondLastValue.temperature > tempLastValue.temperature) {
                return -1;
            } else if (tempSecondLastValue.temperature == tempLastValue.temperature) {
                return 0;
            }
        }
        return 0;
    }

    //WindSpeed Method for Beaufort

    public static int beaufortScale(List<Reading> readings) {
        Reading beaufort = null;
        int n = readings.size();
        if (n > 0) {
            if (n == 1) {
                beaufort = readings.get(0);
            } else {
                beaufort = readings.get(n - 1);
            }

            if (beaufort.windSpeed < 1) {
                return 0;
            } else if (beaufort.windSpeed > 1 && beaufort.windSpeed <= 5) {
                return 1;
            } else if (beaufort.windSpeed >= 6 && beaufort.windSpeed <= 11) {
                return 2;
            } else if (beaufort.windSpeed >= 12 && beaufort.windSpeed <= 19) {
                return 3;
            } else if (beaufort.windSpeed >= 20 && beaufort.windSpeed <= 28) {
                return 4;
            } else if (beaufort.windSpeed >= 29 && beaufort.windSpeed <= 38) {
                return 5;
            } else if (beaufort.windSpeed >= 39 && beaufort.windSpeed <= 49) {
                return 6;
            } else if (beaufort.windSpeed >= 50 && beaufort.windSpeed <= 61) {
                return 7;
            } else if (beaufort.windSpeed >= 62 && beaufort.windSpeed <= 74) {
                return 8;
            } else if (beaufort.windSpeed >= 75 && beaufort.windSpeed <= 88) {
                return 9;
            } else if (beaufort.windSpeed >= 89 && beaufort.windSpeed <= 102) {
                return 10;
            } else if (beaufort.windSpeed >= 103 && beaufort.windSpeed <= 200) {
                return 11;
            } else {
                return 0;
            }

        }
        return 0;
    }


    //Wind Speed Trend

    public static int getWindSpeedTrend(List<Reading> readings) {
        Reading windSpeedSecondLastValue = null;
        Reading windSpeedLastValue = null;
        int n = readings.size();
        if (n > 0) {
            if (n == 1) {
                windSpeedLastValue = readings.get(0);
                windSpeedSecondLastValue = readings.get(0);
            } else {
                windSpeedLastValue = readings.get(n - 1);
                windSpeedSecondLastValue = readings.get(n - 2);
            }

            if (windSpeedSecondLastValue.windSpeed < windSpeedLastValue.windSpeed) {
                return 1;
            } else if (windSpeedSecondLastValue.windSpeed > windSpeedLastValue.windSpeed) {
                return -1;
            } else {
                return 0;
            }
        }
        return 0;
    }

    //WindSpeed methods for max and min speeds

    public static int minimumWindSpeed(List<Reading> readings) {
        Reading minWindSpeed = null;
        if (readings.size() > 0) {
            minWindSpeed = readings.get(0);
            for (Reading reading : readings) {
                if (reading.windSpeed < minWindSpeed.windSpeed) {
                    minWindSpeed = reading;
                }
            }
        }
        return minWindSpeed != null ? minWindSpeed.windSpeed : 0;
    }

    public static int maximumWindSpeed(List<Reading> readings) {
        Reading maxWindSpeed = null;
        if (readings.size() > 0) {
            maxWindSpeed = readings.get(0);
            for (Reading reading : readings) {
                if (reading.windSpeed > maxWindSpeed.windSpeed) {
                    maxWindSpeed = reading;
                }
            }
        }
        return maxWindSpeed != null ? maxWindSpeed.windSpeed : 0;
    }


    //WindDirection method for direction of wind

    public static String windCompassDegree(List<Reading> readings) {
        Reading windDegree = null;
        int n = readings.size();
        if (n > 0) {
            if (n == 1) {
                windDegree = readings.get(0);
            } else {
                windDegree = readings.get(n - 1);
            }
            if (windDegree.windDirection > 348.75 && windDegree.windDirection <= 11.25) {
                return "North";
            }
            if (windDegree.windDirection > 11.25 && windDegree.windDirection <= 33.75) {
                return "North-NorthEast";
            }
            if (windDegree.windDirection > 33.75 && windDegree.windDirection <= 56.25) {
                return "North East";
            }
            if (windDegree.windDirection > 56.25 && windDegree.windDirection <= 78.75) {
                return "East-NorthEast";
            }
            if (windDegree.windDirection > 78.75 && windDegree.windDirection <= 101.25) {
                return "East";
            }
            if (windDegree.windDirection > 101.25 && windDegree.windDirection <= 123.75) {
                return "East-SouthEast";
            }
            if (windDegree.windDirection > 123.75 && windDegree.windDirection <= 146.25) {
                return "SouthEast";
            }
            if (windDegree.windDirection > 146.25 && windDegree.windDirection <= 168.75) {
                return "South-SouthEast";
            }
            if (windDegree.windDirection > 168.75 && windDegree.windDirection <= 191.25) {
                return "South";
            }
            if (windDegree.windDirection > 191.25 && windDegree.windDirection <= 213.75) {
                return "South-SouthWest";
            }
            if (windDegree.windDirection > 213.75 && windDegree.windDirection <= 236.25) {
                return "SouthWest";
            }
            if (windDegree.windDirection > 236.25 && windDegree.windDirection <= 258.75) {
                return "West-SouthWest";
            }
            if (windDegree.windDirection > 258.75 && windDegree.windDirection <= 281.25) {
                return "West";
            }
            if (windDegree.windDirection > 281.25 && windDegree.windDirection <= 303.75) {
                return "West-NorthWest";
            }
            if (windDegree.windDirection > 303.75 && windDegree.windDirection <= 326.25) {
                return "NorthWest";
            }
            if (windDegree.windDirection > 326.25 && windDegree.windDirection <= 348.75) {
                return "North-NorthWest";
            }
        }
        return null;
    }


    //Wind Chill Method

//    public static double windChillMethod(int windSpeed, double temperature) {
//
//        double windChill = 13.12 + 0.6215 * temperature - 11.37 * (Math.pow(windSpeed, 0.16)) + 0.3965 * temperature * (Math.pow(windSpeed, 0.16));
//        return Math.round(windChill * 100.0) / 100.0;
//    }


    public static double windChillMethod(List<Reading> readings) {
        Reading windChill = null;
        double windChillValue = 0;
        int n = readings.size();
        if (n > 0) {
            if (n == 1) {
                windChill = readings.get(0);
            } else {
                windChill = readings.get(n - 1);
            }
            windChillValue = ((13.12 + (0.6215 * windChill.temperature)) - (11.37 * (Math.pow(windChill.windSpeed, 0.16)))) + (0.3965 * windChill.temperature * (Math.pow(windChill.windSpeed, 0.16)));
            return Math.round(windChillValue * 100.0) / 100.0;
        }
        return windChillValue;
    }


    //Pressure Methods

    public static int minimumPressure(List<Reading> readings) {
        Reading minPressure = null;
        if (readings.size() > 0) {
            minPressure = readings.get(0);
            for (Reading reading : readings) {
                if (reading.pressure < minPressure.pressure) {
                    minPressure = reading;
                }
            }
        }
        return minPressure != null ? minPressure.pressure : 0;
    }

    public static int maximumPressure(List<Reading> readings) {
        Reading maxPressure = null;
        if (readings.size() > 0) {
            maxPressure = readings.get(0);
            for (Reading reading : readings) {
                if (reading.pressure > maxPressure.pressure) {
                    maxPressure = reading;
                }
            }
        }
        return maxPressure != null ? maxPressure.pressure : 0;
    }

    public static int getPressureTrend(List<Reading> readings) {
        Reading pressureSecondLastValue = null;
        Reading pressureLastValue = null;
        int n = readings.size();
        if (n > 0) {
            if (n == 1) {
                pressureLastValue = readings.get(0);
                pressureSecondLastValue = readings.get(0);
            } else {
                pressureLastValue = readings.get(n - 1);
                pressureSecondLastValue = readings.get(n - 2);
            }

            if (pressureSecondLastValue.pressure < pressureLastValue.pressure) {
                return 1;
            } else if (pressureSecondLastValue.pressure > pressureLastValue.pressure) {
                return -1;
            } else {
                return 0;
            }
        }
        return 0;
    }
}
