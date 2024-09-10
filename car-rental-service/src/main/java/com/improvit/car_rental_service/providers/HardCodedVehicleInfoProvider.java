package com.improvit.car_rental_service.providers;

import com.improvit.car_rental_service.enums.VehicleType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class HardCodedVehicleInfoProvider implements VehicleInfoProvider {

    @Override
    public BigDecimal getDiscount(VehicleType vehicleType) {
        // Hardcoded discount logic
        if (vehicleType == VehicleType.BUS) {
            return BigDecimal.valueOf(0.02); // 2% discount for buses
        }
        return BigDecimal.ZERO; // No discount for other vehicles
    }

    @Override
    public int getMaxPassengers(VehicleType vehicleType) {
        // Hardcoded passenger capacity logic
        switch (vehicleType) {
            case CAR, SUV:
                return 5;
            case VAN:
                return 7;
            case BUS:
                return 60;
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + vehicleType);
        }
    }
}
