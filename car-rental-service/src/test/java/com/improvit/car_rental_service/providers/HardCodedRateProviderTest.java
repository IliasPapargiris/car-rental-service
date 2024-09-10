package com.improvit.car_rental_service.providers;

import com.improvit.car_rental_service.CarRentalServiceApplication;
import com.improvit.car_rental_service.enums.FuelType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = CarRentalServiceApplication.class)
class HardCodedRateProviderTest {

    @Autowired
    private RateProvider rateProvider;


    @ParameterizedTest
    @CsvSource({
            "PETROL, 0.20",
            "DIESEL, 0.15"
    })
    void testGetBaseRate(FuelType fuelType, BigDecimal expectedRate) {

        //GIVEN - WHEN
        BigDecimal baseRate = rateProvider.getBaseRate(fuelType);

        // THEN
        assertEquals(expectedRate, baseRate,
                String.format("Expected base rate for %s should be %s", fuelType, expectedRate));
    }

    @ParameterizedTest
    @CsvSource({
            "0.10"
    })
    void testGetACCharge(BigDecimal expectedACCharge) {

        //GIVEN - WHEN
        BigDecimal acCharge = rateProvider.getACCharge();

        // THEN
        assertEquals(expectedACCharge, acCharge);
    }


    @ParameterizedTest
    @CsvSource({
            "0.05"
    })
    void testGetExtraPassengerCharge(BigDecimal expectedExtraPassengerCharge) {

        //GIVEN - WHEN
        BigDecimal extraPassengerCharge = rateProvider.getExtraPassengerCharge();

        // THEN
        assertEquals(expectedExtraPassengerCharge, extraPassengerCharge);
    }
}
