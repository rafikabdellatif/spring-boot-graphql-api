package com.graphql.example.repositories;

import com.graphql.example.models.Driver;
import com.graphql.example.models.Vehicle;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static java.util.Objects.nonNull;

@Component
public class VehicleRepository {

    private static final List<Vehicle> vehicles;

    static {
        Vehicle vehicle1 = new Vehicle(1L, "WVGZZZA1ZNK010351", "FB-278-LS", new Driver("Amit", LocalDate.of(1986,10, 19 )));
        Vehicle vehicle2 = new Vehicle(2L, "WVGZZZA1ZNK010348", "ID-278-MS", new Driver("Mohammed", LocalDate.of(1970, 7, 2)));
        vehicles = List.of(vehicle1, vehicle2);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicles;
    }

    public Vehicle vehicleById(final Long id) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.id().equals(id))
                return vehicle;
        }
        return null;
    }

    public Vehicle updateBook(final Long id, final String vin) {
        Vehicle vehicle = vehicleById(id);
        if (nonNull(vehicle)) {
            vehicle.vin(vin);
        }
        return vehicle;
    }
}
