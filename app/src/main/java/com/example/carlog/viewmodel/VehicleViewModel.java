package com.example.carlog.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.carlog.model.Repair;
import com.example.carlog.model.Vehicle;
import com.example.carlog.repository.VehicleRepository;
import java.util.List;

public class VehicleViewModel extends AndroidViewModel {
    private final VehicleRepository repository;
    private final LiveData<List<Vehicle>> allVehicles;

    public VehicleViewModel(@NonNull Application application) {
        super(application);
        repository = new VehicleRepository(application);
        allVehicles = repository.getAllVehicles();
    }

    public LiveData<List<Vehicle>> getAllVehicles() {
        return allVehicles;
    }

    public LiveData<Vehicle> getVehicleById(int id) {
        return repository.getVehicleById(id);
    }

    public void insert(Vehicle vehicle) {
        repository.insertVehicle(vehicle);
    }

    public void update(Vehicle vehicle) {
        repository.updateVehicle(vehicle);
    }

    public void delete(Vehicle vehicle) {
        repository.deleteVehicle(vehicle);
    }

    // Repair related methods
    public LiveData<List<Repair>> getRepairsForVehicle(int vehicleId) {
        return repository.getRepairsForVehicle(vehicleId);
    }

    public void insertRepair(Repair repair) {
        repository.insertRepair(repair);
    }

    public void updateRepair(Repair repair) {
        repository.updateRepair(repair);
    }

    public void deleteRepair(Repair repair) {
        repository.deleteRepair(repair);
    }
}