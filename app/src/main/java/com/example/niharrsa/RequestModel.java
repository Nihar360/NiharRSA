package com.example.niharrsa;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RequestModel {

    private String id;
    private String name;
    private String mobile;
    private String vehicleType;
    private String location;
    private String userId;
    private String status;
    private String date;  // New date attribute

    // Empty constructor (required by Firestore)
    public RequestModel() {
        this.name = "";
        this.mobile = "";
        this.vehicleType = "";
        this.location = "";
        this.userId = "";
        this.status = "Pending";
        this.date = getCurrentDate();  // Set current date as default
    }

    public RequestModel(String name, String mobile, String vehicleType, String location) {
        this.name = name;
        this.mobile = mobile;
        this.vehicleType = vehicleType;
        this.location = location;
        this.date = getCurrentDate();  // Set current date when initialized
    }

    // Getters and setters for the new date attribute
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    // Other getters and setters...
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "RequestModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", location='" + location + '\'' +
                ", userId='" + userId + '\'' +
                ", status='" + status + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    // Helper method to get current date in desired format
    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
}
