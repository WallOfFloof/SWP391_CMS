// DAO: MedicineViewDAO.java
package dao;

import dao.DBContext;
import modelView.MedicineView;
import java.sql.*;

public class MedicineViewDAO {
    private final Connection conn;

    public MedicineViewDAO() {
        this.conn = DBContext.getInstance().getConnection();
    }

    public MedicineView getMedicineDetailById(int medicineId) {
        MedicineView detail = null;
        String sql = "SELECT m.medicine_id, m.name, c.categoryName, u.unitName, m.price, m.ingredient, m.usage, m.expDate, " +
                "d.DistributorName, d.Address, d.DistributorEmail, d.DistributorPhone " +
                "FROM Medicine m " +
                "LEFT JOIN Category c ON m.category_id = c.category_id " +
                "LEFT JOIN Unit u ON m.unit_id = u.unit_id " +
                "LEFT JOIN ImportInfo i ON m.medicine_id = i.medicine_id " +
                "LEFT JOIN Distributor d ON i.DistributorID = d.DistributorID " +
                "WHERE m.medicine_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, medicineId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    detail = new MedicineView();
                    detail.setMedicineId(rs.getInt("medicine_id"));
                    detail.setName(rs.getString("name"));
                    detail.setCategoryName(rs.getString("categoryName"));
                    detail.setUnitName(rs.getString("unitName"));
                    detail.setPrice(rs.getDouble("price"));
                    detail.setIngredient(rs.getString("ingredient"));
                    detail.setUsage(rs.getString("usage"));
                    detail.setExpiration(rs.getString("expDate"));
                    detail.setDistributorName(rs.getString("DistributorName"));
                    detail.setDistributorAddress(rs.getString("Address"));
                    detail.setDistributorEmail(rs.getString("DistributorEmail"));
                    detail.setDistributorPhone(rs.getString("DistributorPhone"));
                    detail.setImageUrl("https://via.placeholder.com/200x240.png?text=Medicine+Image"); // temporary
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }
}