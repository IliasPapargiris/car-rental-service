package com.improvit.car_rental_service.services;

import com.improvit.car_rental_service.enums.FuelType;
import com.improvit.car_rental_service.enums.VehicleType;

import java.math.BigDecimal;

public interface ExpenseCalculator {

    BigDecimal calculateExpense(VehicleType vehicleType, FuelType fuelType, String destination, Integer numberOfPeopleTravelling, Boolean isAirConditioningRequired);

}
