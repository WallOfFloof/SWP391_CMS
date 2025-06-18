package dao;

import model.MedicineDetail;

import java.sql.*;
import java.util.List;

public class PrescriptionDAO {
    private final Connection conn;

    public PrescriptionDAO() {
        this.conn = DBContext.getInstance().getConnection();
    }

    public int createPrescriptionForExistingPatient(
            int patientId,
            int doctorId,
            int pharmacistId,
            List<MedicineDetail> medicines
    ) throws SQLException {
        conn.setAutoCommit(false);
        try {
            String medRecordSQL = "INSERT INTO MedicineRecords (patient_id) VALUES (?)";
            PreparedStatement medRecordPs = conn.prepareStatement(medRecordSQL, Statement.RETURN_GENERATED_KEYS);
            medRecordPs.setInt(1, patientId);
            medRecordPs.executeUpdate();
            ResultSet rsMedRecord = medRecordPs.getGeneratedKeys();
            rsMedRecord.next();
            int medicineRecordId = rsMedRecord.getInt(1);

            String prescriptionSQL = "INSERT INTO Prescription (medicineRecord_id, doctor_id, prescription_date, status) VALUES (?, ?, GETDATE(), 'Pending')";
            PreparedStatement prescriptionPs = conn.prepareStatement(prescriptionSQL, Statement.RETURN_GENERATED_KEYS);
            prescriptionPs.setInt(1, medicineRecordId);
            prescriptionPs.setInt(2, doctorId);
            prescriptionPs.executeUpdate();
            ResultSet rsPrescription = prescriptionPs.getGeneratedKeys();
            rsPrescription.next();
            int prescriptionId = rsPrescription.getInt(1);

            String invoiceSQL = "INSERT INTO Invoice (patient_id, medicineRecord_id, issue_date, total_amount, status) VALUES (?, ?, GETDATE(), 0, 'Pending')";
            PreparedStatement invoicePs = conn.prepareStatement(invoiceSQL, Statement.RETURN_GENERATED_KEYS);
            invoicePs.setInt(1, patientId);
            invoicePs.setInt(2, medicineRecordId);
            invoicePs.executeUpdate();
            ResultSet rsInvoice = invoicePs.getGeneratedKeys();
            rsInvoice.next();
            int invoiceId = rsInvoice.getInt(1);

            String prescriptionInvoiceSQL = "INSERT INTO PrescriptionInvoice (invoice_id, pharmacist_id, prescription_id) VALUES (?, ?, ?)";
            PreparedStatement prescriptionInvoicePs = conn.prepareStatement(prescriptionInvoiceSQL, Statement.RETURN_GENERATED_KEYS);
            prescriptionInvoicePs.setInt(1, invoiceId);
            prescriptionInvoicePs.setInt(2, pharmacistId);
            prescriptionInvoicePs.setInt(3, prescriptionId);
            prescriptionInvoicePs.executeUpdate();
            ResultSet rsPrescriptionInvoice = prescriptionInvoicePs.getGeneratedKeys();
            rsPrescriptionInvoice.next();
            int prescriptionInvoiceId = rsPrescriptionInvoice.getInt(1);

            String medicineSQL = "INSERT INTO Medicines (prescription_invoice_id, medicine_id, quantity, dosage) VALUES (?, ?, ?, ?)";
            for (MedicineDetail med : medicines) {
                PreparedStatement medPs = conn.prepareStatement(medicineSQL);
                medPs.setInt(1, prescriptionInvoiceId);
                medPs.setInt(2, med.getMedicineId());
                medPs.setInt(3, med.getQuantity());
                medPs.setString(4, med.getDosage());
                medPs.executeUpdate();
            }

            String updateInvoiceSQL =
                    "UPDATE Invoice SET total_amount = (" +
                            "  SELECT SUM(med.price * ms.quantity) FROM Medicines ms" +
                            "  JOIN Medicine med ON ms.medicine_id = med.medicine_id" +
                            "  WHERE ms.prescription_invoice_id = ?" +
                            ") WHERE invoice_id = ?";
            PreparedStatement updateInvoicePs = conn.prepareStatement(updateInvoiceSQL);
            updateInvoicePs.setInt(1, prescriptionInvoiceId);
            updateInvoicePs.setInt(2, invoiceId);
            updateInvoicePs.executeUpdate();

            conn.commit();
            return prescriptionId;
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }
}
