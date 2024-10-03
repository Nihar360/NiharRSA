package com.example.niharrsa;

public class RequestModel {

    private String id;
    private String name;
    private String mobile;
    private String vehicleType;
    private String location;
    private String userId; // Optional: Add if you're tracking user ID
    private String status;  // Optional: Add if you're tracking status

    // Empty constructor (required by Firestore)
    public RequestModel() {
        this.name = "";
        this.mobile = "";
        this.vehicleType = "";
        this.location = "";
        this.userId = ""; // Default value
        this.status = "Pending"; // Default status
    }

    public RequestModel(String name, String mobile, String vehicleType, String location) {
        this.name = name;
        this.mobile = mobile;
        this.vehicleType = vehicleType;
        this.location = location;
    }

    // Getters and setters
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
                '}';
    }
}
