package com.example.carlog.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.carlog.model.Repair;
import java.util.List;

@Dao
public interface RepairDao {
    @Insert
    void insert(Repair repair);

    @Update
    void update(Repair repair);

    @Delete
    void delete(Repair repair);

    @Query("SELECT * FROM repairs WHERE vehicleId = :vehicleId ORDER BY date DESC")
    LiveData<List<Repair>> getRepairsForVehicle(int vehicleId);

    @Query("SELECT * FROM repairs WHERE id = :id")
    LiveData<Repair> getRepairById(int id);
}