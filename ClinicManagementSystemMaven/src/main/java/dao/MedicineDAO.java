package dal;

import model.Medicine;

import java.sql.*;
import java.util.*;
import java.math.BigDecimal;

public class MedicineDAO {
    private final Connection conn;

    public MedicineDAO() {
        this.conn = DBContext.getInstance().getConnection();
    }

    // Lấy tất cả thuốc (chỉ lấy các trường cần thiết)
    public List<Medicine> getAllMedicines() {
        List<Medicine> list = new ArrayList<>();
        String sql = "SELECT medicine_id, name, category_id, price, quantity, expDate FROM Medicine";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Medicine m = new Medicine(
                        rs.getInt("medicine_id"),
                        rs.getNString("name"),
                        rs.getInt("category_id"),
                        rs.getBigDecimal("price"),
                        rs.getInt("quantity"),
                        rs.getDate("expDate")
                );
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy theo ID (nếu cần)
    public Medicine getMedicineById(int id) {
        String sql = "SELECT medicine_id, name, category_id, price, quantity, expDate FROM Medicine WHERE medicine_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Medicine(
                            rs.getInt("medicine_id"),
                            rs.getNString("name"),
                            rs.getInt("category_id"),
                            rs.getBigDecimal("price"),
                            rs.getInt("quantity"),
                            rs.getDate("expDate")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean addMedicine(Medicine med) {
        String sql = "INSERT INTO Medicine (name, category_id, price, quantity, expDate) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setNString(1, med.getName());
            ps.setInt(2, med.getCategory_id());
            ps.setBigDecimal(3, med.getPrice());
            ps.setInt(4, med.getQuantity());
            ps.setDate(5, med.getExpDate());
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        med.setMedicine_id(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Update thuốc
    public boolean updateMedicine(Medicine med) {
        String sql = "UPDATE Medicine SET name=?, category_id=?, price=?, quantity=?, expDate=? WHERE medicine_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setNString(1, med.getName());
            ps.setInt(2, med.getCategory_id());
            ps.setBigDecimal(3, med.getPrice());
            ps.setInt(4, med.getQuantity());
            ps.setDate(5, med.getExpDate());
            ps.setInt(6, med.getMedicine_id());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteMedicine(int id) {
        String sql = "DELETE FROM Medicine WHERE medicine_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}