package controller;

import com.google.gson.Gson;
import dao.MedicineRevenueStatDAO;
import dao.DBContext;
import modelView.MedicineRevenueStat;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/api/medicine-revenue-stat")
public class MedicineRevenueStatServlet extends HttpServlet {
    private final Gson gson = new Gson();
    private Connection conn;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            // Lấy connection duy nhất lúc khởi động servlet
            conn = DBContext.getInstance().getConnection();
        } catch (Exception e) {
            throw new ServletException("Không thể mở kết nối DB", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setContentType("application/json;charset=UTF-8");

        // Lấy param
        String fromStr = req.getParameter("fromDate");
        String toStr   = req.getParameter("toDate");
        String sortBy  = req.getParameter("sortBy");

        // Default nếu parse lỗi hoặc trống
        Date defaultFrom = Date.valueOf("2024-01-01");
        Date defaultTo   = Date.valueOf(LocalDate.now());
        Date fromDate, toDate;
        try {
            fromDate = (fromStr != null && !fromStr.isEmpty())
                    ? tryParseDate(fromStr, defaultFrom)
                    : defaultFrom;
            toDate = (toStr != null && !toStr.isEmpty())
                    ? tryParseDate(toStr, defaultTo)
                    : defaultTo;
            if (fromDate.after(toDate)) {
                Date tmp = fromDate; fromDate = toDate; toDate = tmp;
            }
        } catch (Exception e) {
            fromDate = defaultFrom;
            toDate = defaultTo;
        }

        try {
            // Dùng luôn conn đã khởi tạo, KHÔNG đóng ở đây
            MedicineRevenueStatDAO dao = new MedicineRevenueStatDAO(conn);
            List<MedicineRevenueStat> stats = dao.getRevenueStats(fromDate, toDate, sortBy);
            resp.getWriter().write(gson.toJson(stats));
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\":\"Internal server error\"}");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        if (conn != null) {
            try {
                conn.close();  // Đóng connection khi servlet bị dừng
            } catch (Exception ignored) {}
        }
    }

    // Helper: thử parse yyyy-MM-dd rồi dd/MM/yyyy
    private Date tryParseDate(String s, Date fallback) {
        try {
            return Date.valueOf(s);
        } catch (IllegalArgumentException ex) {
            LocalDate ld = LocalDate.parse(s, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return Date.valueOf(ld);
        }
    }
}
