package com.example.carlog.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "repairs",
        foreignKeys = @ForeignKey(entity = Vehicle.class,
                parentColumns = "id",
                childColumns = "vehicleId",
                onDelete = ForeignKey.CASCADE),
        indices = {@Index("vehicleId")})
public class Repair {
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private int vehicleId;
    private String title;
    private String description;
    private double cost;
    private long date; // Unix timestamp
    private String partsChanged;

    public Repair(int vehicleId, String title, String description, double cost, long date, String partsChanged) {
        this.vehicleId = vehicleId;
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.date = date;
        this.partsChanged = partsChanged;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }

    public long getDate() { return date; }
    public void setDate(long date) { this.date = date; }

    public String getPartsChanged() { return partsChanged; }
    public void setPartsChanged(String partsChanged) { this.partsChanged = partsChanged; }
}