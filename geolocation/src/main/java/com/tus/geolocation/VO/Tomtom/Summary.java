package com.tus.geolocation.VO.Tomtom;

import java.util.Date;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
public class Summary {
    public int lengthInMeters;
    public int travelTimeInSeconds;
    public int trafficDelayInSeconds;
    public int trafficLengthInMeters;
    public Date departureTime;
    public Date arrivalTime;
}
