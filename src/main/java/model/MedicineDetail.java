package model;

public class MedicineDetail {
    private int medicineId;
    private int quantity;
    private String dosage;

    public MedicineDetail() {

    }

    public MedicineDetail(int medicineId, int quantity, String dosage) {
        this.medicineId = medicineId;
        this.quantity = quantity;
        this.dosage = dosage;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }
}
