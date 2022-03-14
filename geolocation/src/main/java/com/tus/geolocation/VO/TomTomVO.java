package com.tus.geolocation.VO;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class TomTomVO {
    public String formatVersion;
    public ArrayList<Route> routes;

    @Data
    public static class Route{
        public Summary summary;
        public ArrayList<Leg> legs;
        public ArrayList<Section> sections;
    }

    @Data
    public static class Summary{
        public int lengthInMeters;
        public int travelTimeInSeconds;
        public int trafficDelayInSeconds;
        public int trafficLengthInMeters;
        public Date departureTime;
        public Date arrivalTime;
    }

    @Data
    public static class Point{
        public double latitude;
        public double longitude;
    }

    @Data
    public static class Leg{
        public Summary summary;
        public ArrayList<Point> points;
    }

    @Data
    public static class Section{
        public int startPointIndex;
        public int endPointIndex;
        public String sectionType;
        public String travelMode;
    }


}








