package com.example.niharrsa;

public class MechanicModel {

    private String id;
    private String mechanicName;
    private String mechanicLocation;
    private String mechanicPrice;
    private String vehicleName;

    // Empty constructor (required by Firestore)
    public MechanicModel() {
        this.mechanicName = "";
        this.mechanicLocation = "";
        this.mechanicPrice = "";
        this.vehicleName = "";
    }

    public MechanicModel(String mechanicName, String mechanicLocation, String mechanicPrice, String vehicleName) {
        this.mechanicName = mechanicName;
        this.mechanicLocation = mechanicLocation;
        this.mechanicPrice = mechanicPrice;
        this.vehicleName = vehicleName;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getMechanicName() { return mechanicName; }
    public void setMechanicName(String mechanicName) { this.mechanicName = mechanicName; }

    public String getMechanicLocation() { return mechanicLocation; }
    public void setMechanicLocation(String mechanicLocation) { this.mechanicLocation = mechanicLocation; }

    public String getMechanicPrice() { return mechanicPrice; }
    public void setMechanicPrice(String mechanicPrice) { this.mechanicPrice = mechanicPrice; }

    public String getVehicleName() { return vehicleName; }
    public void setVehicleName(String vehicleName) { this.vehicleName = vehicleName; }

    @Override
    public String toString() {
        return "MechanicModel{" +
                "id='" + id + '\'' +
                ", mechanicName='" + mechanicName + '\'' +
                ", mechanicLocation='" + mechanicLocation + '\'' +
                ", mechanicPrice='" + mechanicPrice + '\'' +
                ", vehicleName='" + vehicleName + '\'' +
                '}';
    }
}
