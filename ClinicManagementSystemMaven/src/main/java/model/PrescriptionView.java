package model;

import java.util.Date;

public class PrescriptionView {
    private int prescriptionId;
    private String patientName;
    private String doctorName;
    private Date prescriptionDate;
    private String status;
    private double totalAmount;

    public PrescriptionView() {}

    public PrescriptionView(int prescriptionId, String patientName, String doctorName, Date prescriptionDate, String status, double totalAmount) {
        this.prescriptionId = prescriptionId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.prescriptionDate = prescriptionDate;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }
    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }
    public String getPatientName() {
        return patientName;
    }
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
    public String getDoctorName() {
        return doctorName;
    }
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
    public Date getPrescriptionDate() {
        return prescriptionDate;
    }
    public void setPrescriptionDate(Date prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
