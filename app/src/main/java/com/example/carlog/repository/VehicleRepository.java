package com.example.carlog.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.carlog.database.AppDatabase;
import com.example.carlog.database.dao.RepairDao;
import com.example.carlog.database.dao.VehicleDao;
import com.example.carlog.model.Repair;
import com.example.carlog.model.Vehicle;
import java.util.List;

public class VehicleRepository {
    private final VehicleDao vehicleDao;
    private final RepairDao repairDao;
    private final LiveData<List<Vehicle>> allVehicles;

    public VehicleRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        vehicleDao = db.vehicleDao();
        repairDao = db.repairDao();
        allVehicles = vehicleDao.getAllVehicles();
    }

    // Vehicle operations
    public LiveData<List<Vehicle>> getAllVehicles() {
        return allVehicles;
    }

    public LiveData<Vehicle> getVehicleById(int id) {
        return vehicleDao.getVehicleById(id);
    }

    public void insertVehicle(Vehicle vehicle) {
        AppDatabase.databaseWriteExecutor.execute(() -> vehicleDao.insert(vehicle));
    }

    public void updateVehicle(Vehicle vehicle) {
        AppDatabase.databaseWriteExecutor.execute(() -> vehicleDao.update(vehicle));
    }

    public void deleteVehicle(Vehicle vehicle) {
        AppDatabase.databaseWriteExecutor.execute(() -> vehicleDao.delete(vehicle));
    }

    // Repair operations
    public LiveData<List<Repair>> getRepairsForVehicle(int vehicleId) {
        return repairDao.getRepairsForVehicle(vehicleId);
    }

    public void insertRepair(Repair repair) {
        AppDatabase.databaseWriteExecutor.execute(() -> repairDao.insert(repair));
    }

    public void updateRepair(Repair repair) {
        AppDatabase.databaseWriteExecutor.execute(() -> repairDao.update(repair));
    }

    public void deleteRepair(Repair repair) {
        AppDatabase.databaseWriteExecutor.execute(() -> repairDao.delete(repair));
    }
}