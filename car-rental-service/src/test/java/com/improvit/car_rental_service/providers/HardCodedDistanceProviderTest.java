package com.improvit.car_rental_service.providers;

import com.improvit.car_rental_service.CarRentalServiceApplication;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CarRentalServiceApplication.class)
class HardCodedDistanceProviderTest {

    @Autowired
    private DistanceProvider distanceProvider;

    @ParameterizedTest
    @CsvSource({
            "Berlin, 0",
            "Munich, 584",
            "Hamburg, 289",
            "Frankfurt, 545",
            "Cologne, 576"
    })
    void testGetDistance(String destination, int expectedDistance) {

        //GIVEN - WHEN
        int distance = distanceProvider.getDistance(destination);

        //THEN
        assertEquals(expectedDistance, distance,
                String.format("Expected distance for %s should be %d", destination, expectedDistance));
    }

    @ParameterizedTest
    @CsvSource({"Lamia"})
    void testGetDistanceForUnknownDestination(String destination) {

        //GIVEN - WHEN - THEN
        assertThrows(IllegalArgumentException.class, () -> distanceProvider.getDistance(destination),
                String.format("Expected IllegalArgumentException for unknown destination: %s", destination));
    }
}
