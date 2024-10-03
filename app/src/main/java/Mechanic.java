public class Mechanic {
    private String mechanicName;
    private String mechanicNumber;
    private String mechanicLocation;
    private String mechanicPrice;
    private String vehicleName;
    private String id; // Firestore document ID

    // Empty constructor for Firestore
    public Mechanic() {}

    // Constructor to initialize all fields
    public Mechanic(String mechanicName, String mechanicNumber, String mechanicLocation, String mechanicPrice, String vehicleName, String id) {
        this.mechanicName = mechanicName;
        this.mechanicNumber = mechanicNumber;
        this.mechanicLocation = mechanicLocation;
        this.mechanicPrice = mechanicPrice;
        this.vehicleName = vehicleName;
        this.id = id;
    }

    // Getters and Setters for each field
    public String getMechanicName() {
        return mechanicName;
    }

    public void setMechanicName(String mechanicName) {
        this.mechanicName = mechanicName;
    }

    public String getMechanicNumber() {
        return mechanicNumber;
    }

    public void setMechanicNumber(String mechanicNumber) {
        this.mechanicNumber = mechanicNumber;
    }

    public String getMechanicLocation() {
        return mechanicLocation;
    }

    public void setMechanicLocation(String mechanicLocation) {
        this.mechanicLocation = mechanicLocation;
    }

    public String getMechanicPrice() {
        return mechanicPrice;
    }

    public void setMechanicPrice(String mechanicPrice) {
        this.mechanicPrice = mechanicPrice;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
