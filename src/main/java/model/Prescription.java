package model;

import java.time.LocalDate;

public class Prescription {
    private int prescriptionID;
    private double totalPrice;
    private LocalDate sellDate;
    private String prescribedBy;
    private int patientID;
    private String patientName;
    private String patientPhone;

    public Prescription() {
    }

    public int getPrescriptionID() {
        return prescriptionID;
    }

    public void setPrescriptionID(int prescriptionID) {
        this.prescriptionID = prescriptionID;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getSellDate() {
        return sellDate;
    }

    public void setSellDate(LocalDate sellDate) {
        this.sellDate = sellDate;
    }

    public String getPrescribedBy() {
        return prescribedBy;
    }

    public void setPrescribedBy(String prescribedBy) {
        this.prescribedBy = prescribedBy;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public Prescription(int prescriptionID, double totalPrice, LocalDate sellDate, String prescribedBy, int patientID, String patientName, String patientPhone) {
        this.prescriptionID = prescriptionID;
        this.totalPrice = totalPrice;
        this.sellDate = sellDate;
        this.prescribedBy = prescribedBy;
        this.patientID = patientID;
        this.patientName = patientName;
        this.patientPhone = patientPhone;
    }
}
