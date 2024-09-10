package com.improvit.car_rental_service.providers;

import com.improvit.car_rental_service.CarRentalServiceApplication;
import com.improvit.car_rental_service.enums.VehicleType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CarRentalServiceApplication.class)
class HardCodedDiscountProviderTest {


    @Autowired
    private VehicleInfoProvider vehicleInfoProvider;

    @ParameterizedTest
    @EnumSource(value = VehicleType.class)
    void testGetDiscount(VehicleType vehicleType) {
        //GIVEN
        BigDecimal expectedDiscount;

        if (vehicleType == VehicleType.BUS) {
            expectedDiscount = BigDecimal.valueOf(0.02);
        } else {
            expectedDiscount = BigDecimal.ZERO;
        }

        //WHEN
        BigDecimal discount = vehicleInfoProvider.getDiscount(vehicleType);

        //THEN
        assertEquals(expectedDiscount, discount);
    }


    @ParameterizedTest
    @CsvSource({
            "CAR, 5",
            "SUV, 5",
            "VAN, 7",
            "BUS, 60"
    })
    void testGetMaxPassengers(VehicleType vehicleType, int expectedMaxPassengers) {

        //GIVEN - WHEN
        int maxPassengers = vehicleInfoProvider.getMaxPassengers(vehicleType);

        //THEN
        assertEquals(expectedMaxPassengers, maxPassengers);
    }

    @ParameterizedTest
    @CsvSource({
            "Unknown"
    })
    void testGetMaxPassengersForUnknownVehicleType(String unknownVehicleType) {
        //GIVEN - WHEN -THEN
        assertThrows(IllegalArgumentException.class,
                () -> vehicleInfoProvider.getMaxPassengers(VehicleType.valueOf(unknownVehicleType)));
    }
}
