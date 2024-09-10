package com.improvit.car_rental_service.services;

import com.improvit.car_rental_service.CarRentalServiceApplication;
import com.improvit.car_rental_service.enums.FuelType;
import com.improvit.car_rental_service.enums.VehicleType;
import com.improvit.car_rental_service.providers.DistanceProvider;
import com.improvit.car_rental_service.providers.RateProvider;
import com.improvit.car_rental_service.providers.VehicleInfoProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = CarRentalServiceApplication.class)
public class ExpenseCalculatorImplTest {


    @Autowired
    private ExpenseCalculator expenseCalculator;

    @Autowired
    private DistanceProvider distanceProvider;

    @Autowired
    private RateProvider rateProvider;

    @Autowired
    private VehicleInfoProvider vehicleInfoProvider;


    @ParameterizedTest
    @CsvSource({
            "CAR, PETROL, Munich, 5, false, 116.80",
            "VAN, DIESEL, Frankfurt, 7, false, 81.75",
            "BUS, DIESEL, Cologne, 40, false, 84.6720",
            "CAR, PETROL, Berlin, 5, false, 0.00"
    })
    void testCalculateExpense_NoAC_WithinCapacity(VehicleType vehicleType, FuelType fuelType, String destination,
                                                  Integer numberOfPeopleTravelling, Boolean isAirConditioningRequired,
                                                  BigDecimal expectedExpense) {
        //GIVEN - WHEN
        BigDecimal actualExpense = expenseCalculator.calculateExpense(vehicleType, fuelType, destination, numberOfPeopleTravelling, isAirConditioningRequired);

        //THEN
        assertEquals(expectedExpense, actualExpense,
                String.format("Expected expense for vehicle type %s with fuel %s to destination %s should be %s",
                        vehicleType, fuelType, destination, expectedExpense));
    }

    @ParameterizedTest
    @CsvSource({
            "CAR, PETROL, Munich, 5, true, 175.20",
            "SUV, PETROL, Frankfurt, 5, true, 163.50",
            "VAN, DIESEL, Cologne, 7, true, 144.00"
    })
    void testCalculateExpense_WithAC_WithinCapacity(VehicleType vehicleType, FuelType fuelType, String destination,
                                                    Integer numberOfPeopleTravelling, Boolean isAirConditioningRequired,
                                                    BigDecimal expectedExpense) {
        //GIVEN - WHEN
        BigDecimal actualExpense = expenseCalculator.calculateExpense(vehicleType, fuelType, destination, numberOfPeopleTravelling, isAirConditioningRequired);

        //THEN
        assertEquals(expectedExpense, actualExpense,
                String.format("Expected expense for vehicle type %s with fuel %s to destination %s with AC should be %s",
                        vehicleType, fuelType, destination, expectedExpense));
    }


    @ParameterizedTest
    @CsvSource({"VAN, PETROL, Hamburg, 9, true, 115.60"})
    void testCalculateExpense_WithAC_ExceedsCapacity(VehicleType vehicleType, FuelType fuelType, String destination,
                                                     Integer numberOfPeopleTravelling, Boolean isAirConditioningRequired,
                                                     BigDecimal expectedExpense) {


        //GIVEN - WHEN
        BigDecimal actualExpense = expenseCalculator.calculateExpense(vehicleType, fuelType, destination, numberOfPeopleTravelling, isAirConditioningRequired);

        //THEN
        assertEquals(expectedExpense, actualExpense,
                String.format("Expected expense for vehicle type %s with fuel %s to destination %s with AC and extra passengers should be %s",
                        vehicleType, fuelType, destination, expectedExpense));
    }

    @ParameterizedTest
    @CsvSource({
            "BUS, DIESEL, Munich, 52, true, 143.0800"
    })
    void testCalculateExpense_Bus_WithDiscountAndAC(VehicleType vehicleType, FuelType fuelType, String destination,
                                                    Integer numberOfPeopleTravelling, Boolean isAirConditioningRequired,
                                                    BigDecimal expectedExpense) {
        //GIVEN - WHEN
        BigDecimal actualExpense = expenseCalculator.calculateExpense(vehicleType, fuelType, destination, numberOfPeopleTravelling, isAirConditioningRequired);

        //THEN
        assertEquals(expectedExpense, actualExpense,
                String.format("Expected expense for bus with fuel %s to destination %s with discount and AC should be %s",
                        fuelType, destination, expectedExpense));
    }
}
