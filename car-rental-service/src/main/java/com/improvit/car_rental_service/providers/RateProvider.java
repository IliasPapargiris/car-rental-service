package com.improvit.car_rental_service.providers;

import com.improvit.car_rental_service.enums.FuelType;

import java.math.BigDecimal;

public interface RateProvider {

    BigDecimal getBaseRate(FuelType fuelType);

    BigDecimal getACCharge();

    BigDecimal getExtraPassengerCharge();

}
