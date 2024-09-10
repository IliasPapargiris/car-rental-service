package com.improvit.car_rental_service.services;

import com.improvit.car_rental_service.enums.FuelType;
import com.improvit.car_rental_service.enums.VehicleType;
import com.improvit.car_rental_service.providers.DistanceProvider;
import com.improvit.car_rental_service.providers.RateProvider;
import com.improvit.car_rental_service.providers.VehicleInfoProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ExpenseCalculatorImpl implements ExpenseCalculator{

    private final DistanceProvider distanceProvider;
    private final VehicleInfoProvider vehicleInfoProvider;
    private final RateProvider rateProvider;

    @Override
    public BigDecimal calculateExpense(VehicleType vehicleType, FuelType fuelType, String destination, Integer numberOfPeopleTravelling, Boolean isAirConditioningRequired) {
        int distance = getDistance(destination);
        BigDecimal baseRate = calculateBaseRate(fuelType, isAirConditioningRequired);
        BigDecimal discount = calculateDiscount(vehicleType);
        BigDecimal baseCost = calculateBaseCost(baseRate, distance, discount);

        baseCost = addExceedingPassengersCharge(baseCost, vehicleType, numberOfPeopleTravelling, distance);

        return baseCost;
    }

    private int getDistance(String destination) {
        return distanceProvider.getDistance(destination);
    }

    private BigDecimal calculateBaseRate(FuelType fuelType, Boolean isAirConditioningRequired) {
        BigDecimal baseRate = rateProvider.getBaseRate(fuelType);
        BigDecimal acCharge = isAirConditioningRequired ? rateProvider.getACCharge() : BigDecimal.ZERO;
        return baseRate.add(acCharge);
    }

    private BigDecimal calculateDiscount(VehicleType vehicleType) {
        return vehicleInfoProvider.getDiscount(vehicleType);
    }

    private BigDecimal calculateBaseCost(BigDecimal baseRate, int distance, BigDecimal discount) {
        return baseRate.multiply(BigDecimal.valueOf(distance)).multiply(BigDecimal.ONE.subtract(discount));
    }

    private BigDecimal addExceedingPassengersCharge(BigDecimal baseCost, VehicleType vehicleType, Integer numberOfPeopleTravelling, int distance) {
        int maxPassengers = vehicleInfoProvider.getMaxPassengers(vehicleType);
        if (numberOfPeopleTravelling > maxPassengers) {
            int extraPassengers = numberOfPeopleTravelling - maxPassengers;

            BigDecimal extraPassengersCost = rateProvider.getExtraPassengerCharge()
                    .multiply(BigDecimal.valueOf(extraPassengers))
                    .multiply(BigDecimal.valueOf(distance));
            baseCost = baseCost.add(extraPassengersCost);
        }

        return baseCost;
    }
}
