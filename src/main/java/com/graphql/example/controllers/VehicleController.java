package com.graphql.example.controllers;

import com.graphql.example.models.Vehicle;
import com.graphql.example.repositories.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleRepository vehicleRepository;

    @SchemaMapping(typeName = "Query", value = "allVehicles")
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.getAllVehicles();
    }

    @QueryMapping
    public Vehicle vehicleById(@Argument Long id) {
        return vehicleRepository.vehicleById(id);
    }

    @MutationMapping
    public Vehicle addVehicle(@Argument Long id, @Argument String vin) {
        return vehicleRepository.updateBook(id, vin);
    }

}
