package com.improvit.car_rental_service.providers;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class HardCodedDistanceProvider implements DistanceProvider {


    private static final Map<String, Integer> DISTANCES = Map.of(
            "Berlin", 0,
            "Munich", 584,
            "Hamburg", 289,
            "Frankfurt", 545,
            "Cologne", 576
    );

    @Override
    public int getDistance(String destination) {
        if (!DISTANCES.containsKey(destination)) {
            throw new IllegalArgumentException(String.format("Non existing destination %s", destination));
        }
        return DISTANCES.get(destination);
    }
}

