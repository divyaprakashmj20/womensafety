package com.tus.geolocation.services;

import com.tus.geolocation.VO.TomTomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GeoLocationService {

    @Autowired
    private WebClient.Builder webclientBuilder;

    public TomTomVO.Summary getLocationData(@RequestParam double fromLatitude, @RequestParam double fromLongitude, @RequestParam double toLatitude, @RequestParam double toLongitude) {

        System.out.println(fromLatitude + fromLongitude + toLatitude + toLongitude);
        TomTomVO tomTomVO = webclientBuilder.build()
                .get()
                .uri("https://api.tomtom.com/routing/1/calculateRoute/" + fromLatitude + "," + fromLongitude + ":" + toLatitude + "," + toLongitude + "/json?key=HFIEN2N8PyYo32M6zjeAEgRmQyrLQAAl")
                .retrieve()
                .bodyToMono(TomTomVO.class)
                .block();
        return tomTomVO.getRoutes().get(0).getSummary();
    }

}
