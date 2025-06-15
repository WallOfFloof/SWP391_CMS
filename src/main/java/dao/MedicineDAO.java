package dao;

import model.Medicine;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class MedicineDAO {
    private final Connection conn;

    public MedicineDAO() {
        this.conn = DBContext.getInstance().getConnection();
    }

    // Lấy tất cả thuốc
    public List<Medicine> getAllMedicines() {
        List<Medicine> list = new ArrayList<>();
        String sql = "SELECT medicine_id, name, unit_id, category_id, ingredient, usage, preservation, manuDate, expDate, quantity, price, warehouse_id FROM Medicine";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Medicine m = new Medicine(
                        rs.getInt("medicine_id"),
                        rs.getString("name"),
                        rs.getInt("unit_id"),
                        rs.getInt("category_id"),
                        rs.getString("ingredient"),
                        rs.getString("usage"),
                        rs.getString("preservation"),
                        rs.getDate("manuDate") != null ? rs.getDate("manuDate").toLocalDate() : null,
                        rs.getDate("expDate") != null ? rs.getDate("expDate").toLocalDate() : null,
                        rs.getInt("quantity"),
                        rs.getBigDecimal("price"),
                        rs.getInt("warehouse_id")
                );
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy thuốc theo ID
    public Medicine getMedicineById(int id) {
        String sql = "SELECT medicine_id, name, unit_id, category_id, ingredient, usage, preservation, manuDate, expDate, quantity, price, warehouse_id FROM Medicine WHERE medicine_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Medicine(
                            rs.getInt("medicine_id"),
                            rs.getString("name"),
                            rs.getInt("unit_id"),
                            rs.getInt("category_id"),
                            rs.getString("ingredient"),
                            rs.getString("usage"),
                            rs.getString("preservation"),
                            rs.getDate("manuDate") != null ? rs.getDate("manuDate").toLocalDate() : null,
                            rs.getDate("expDate") != null ? rs.getDate("expDate").toLocalDate() : null,
                            rs.getInt("quantity"),
                            rs.getBigDecimal("price"),
                            rs.getInt("warehouse_id")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Thêm mới thuốc
    public boolean addMedicine(Medicine med) {
        String sql = "INSERT INTO Medicine (name, unit_id, category_id, ingredient, usage, preservation, manuDate, expDate, quantity, price, warehouse_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, med.getName());
            ps.setInt(2, med.getUnitId());
            ps.setInt(3, med.getCategoryId());
            ps.setString(4, med.getIngredient());
            ps.setString(5, med.getUsage());
            ps.setString(6, med.getPreservation());
            if (med.getManuDate() != null) ps.setDate(7, Date.valueOf(med.getManuDate())); else ps.setNull(7, Types.DATE);
            if (med.getExpDate() != null) ps.setDate(8, Date.valueOf(med.getExpDate())); else ps.setNull(8, Types.DATE);
            ps.setInt(9, med.getQuantity());
            ps.setBigDecimal(10, med.getPrice());
            ps.setInt(11, med.getWarehouseId());
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        med.setMedicineId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật thuốc
    public boolean updateMedicine(Medicine med) {
        String sql = "UPDATE Medicine SET name=?, unit_id=?, category_id=?, ingredient=?, usage=?, preservation=?, manuDate=?, expDate=?, quantity=?, price=?, warehouse_id=? WHERE medicine_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, med.getName());
            ps.setInt(2, med.getUnitId());
            ps.setInt(3, med.getCategoryId());
            ps.setString(4, med.getIngredient());
            ps.setString(5, med.getUsage());
            ps.setString(6, med.getPreservation());
            if (med.getManuDate() != null) ps.setDate(7, Date.valueOf(med.getManuDate())); else ps.setNull(7, Types.DATE);
            if (med.getExpDate() != null) ps.setDate(8, Date.valueOf(med.getExpDate())); else ps.setNull(8, Types.DATE);
            ps.setInt(9, med.getQuantity());
            ps.setBigDecimal(10, med.getPrice());
            ps.setInt(11, med.getWarehouseId());
            ps.setInt(12, med.getMedicineId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa thuốc
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