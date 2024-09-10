package com.improvit.car_rental_service.providers;

import com.improvit.car_rental_service.enums.VehicleType;

import java.math.BigDecimal;

public interface VehicleInfoProvider {

    /**
     * Get the discount for the given vehicle type.
     *
     * @param vehicleType The type of vehicle (Car/Bus/Van etc.)
     * @return The discount rate for the vehicle type (e.g., 2% for buses).
     */
    BigDecimal getDiscount(VehicleType vehicleType);

    /**
     * Get the maximum number of passengers allowed for the given vehicle type.
     *
     * @param vehicleType The type of vehicle (Car/Bus/Van etc.)
     * @return The maximum number of passengers allowed.
     */
    int getMaxPassengers(VehicleType vehicleType);
}
