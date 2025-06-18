package dao;

import modelView.MedicineRevenueStat;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MedicineRevenueStatDAO {
    private final Connection conn;

    public MedicineRevenueStatDAO(Connection conn) {
        this.conn = conn;
    }

    public List<MedicineRevenueStat> getRevenueStats(Date fromDate, Date toDate, String sortBy) throws SQLException {
        List<MedicineRevenueStat> list = new ArrayList<>();

        // Xác định ORDER BY dựa trên sortBy
        String orderBy = "total_quantity_sold DESC, total_revenue DESC";
        if ("revenue".equalsIgnoreCase(sortBy)) {
            orderBy = "total_revenue DESC, total_quantity_sold DESC";
        }

        String sql =
                "SELECT "
                        + "  m.medicine_id, "
                        + "  m.name AS medicine_name, "
                        + "  u.unitName AS unit, "
                        + "  SUM(ms.quantity) AS total_quantity_sold, "
                        + "  SUM(ms.quantity * m.price) AS total_revenue "
                        + "FROM Medicines ms "
                        + "INNER JOIN Medicine m ON ms.medicine_id = m.medicine_id "
                        + "INNER JOIN Unit u ON m.unit_id = u.unit_id "
                        + "INNER JOIN PrescriptionInvoice pi ON ms.prescription_invoice_id = pi.prescription_invoice_id "
                        + "INNER JOIN Prescription p ON pi.prescription_id = p.prescription_id "
                        + "WHERE p.status = 'Dispensed' "
                        + "  AND p.prescription_date BETWEEN ? AND ? "
                        + "GROUP BY m.medicine_id, m.name, u.unitName "
                        + "ORDER BY " + orderBy;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            // Bind ngày tháng vào vị trí tham số
            ps.setDate(1, fromDate);
            ps.setDate(2, toDate);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new MedicineRevenueStat(
                            rs.getInt("medicine_id"),
                            rs.getString("medicine_name"),
                            rs.getString("unit"),
                            rs.getInt("total_quantity_sold"),
                            rs.getDouble("total_revenue")
                    ));
                }
            }
        }

        return list;
    }
}
