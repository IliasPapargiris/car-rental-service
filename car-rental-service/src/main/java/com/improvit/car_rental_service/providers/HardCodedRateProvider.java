package com.improvit.car_rental_service.providers;

import com.improvit.car_rental_service.enums.FuelType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class HardCodedRateProvider implements RateProvider {

    private static final BigDecimal PETROL_RATE = new BigDecimal("0.20");
    private static final BigDecimal DIESEL_DISCOUNT = new BigDecimal("0.05");
    private static final BigDecimal AC_CHARGE = new BigDecimal("0.10");
    private static final BigDecimal EXTRA_PASSENGER_CHARGE = new BigDecimal("0.05");

    @Override
    public BigDecimal getBaseRate(FuelType fuelType) {
        if (fuelType == FuelType.DIESEL) {
            return PETROL_RATE.subtract(DIESEL_DISCOUNT);
        }
        return PETROL_RATE;
    }

    @Override
    public BigDecimal getACCharge() {
        return AC_CHARGE;
    }

    @Override
    public BigDecimal getExtraPassengerCharge() {
        return EXTRA_PASSENGER_CHARGE;
    }
}
