package com.tus.geolocation.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    Double latitude;
    Double longitude;
//
//
//    public static void main(String[] args) {
//        ArrayList<Location> l = new ArrayList<>();
//        l.add(new Location(1.122,2.3213));
//        l.add(new Location(1.33,4.222));
//
//        String s = l.stream().map(i->i.getLatitude() + "," + i.getLongitude()).collect(Collectors.joining(":"));
//
//        System.out.println(s);
//    }
}
