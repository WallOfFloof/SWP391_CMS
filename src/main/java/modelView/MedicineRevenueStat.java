package modelView;

public class MedicineRevenueStat {
    private int medicineId;
    private String medicineName;
    private String unit;
    private int totalQuantitySold;
    private double totalRevenue;

    public MedicineRevenueStat(int medicineId, String medicineName, String unit, int totalQuantitySold, double totalRevenue) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.unit = unit;
        this.totalQuantitySold = totalQuantitySold;
        this.totalRevenue = totalRevenue;
    }

    // Getters & Setters
    public int getMedicineId() { return medicineId; }
    public void setMedicineId(int medicineId) { this.medicineId = medicineId; }

    public String getMedicineName() { return medicineName; }
    public void setMedicineName(String medicineName) { this.medicineName = medicineName; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public int getTotalQuantitySold() { return totalQuantitySold; }
    public void setTotalQuantitySold(int totalQuantitySold) { this.totalQuantitySold = totalQuantitySold; }

    public double getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(double totalRevenue) { this.totalRevenue = totalRevenue; }
}