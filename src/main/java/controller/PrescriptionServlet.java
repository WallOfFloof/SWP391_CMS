package controller;

import com.google.gson.Gson;
import dao.PrescriptionDAO;
import dao.PrescriptionViewDAO;
import modelView.PrescriptionView;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import model.MedicineDetail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api/prescriptions")
public class PrescriptionServlet extends HttpServlet {
    private final PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
    private final Gson gson = new Gson();

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String presIdStr = req.getParameter("prescriptionId");
        if (presIdStr == null || presIdStr.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Missing prescriptionId parameter\"}");
            return;
        }

        try {
            int prescriptionId = Integer.parseInt(presIdStr);

            // Gọi DAO/phương thức lấy chi tiết đơn thuốc
            // (Bạn có thể dùng PrescriptionViewDAO nếu đã có, hoặc viết DAO lấy theo ID)
            PrescriptionViewDAO viewDAO = new PrescriptionViewDAO();
            List<PrescriptionView> result = viewDAO.searchPrescriptionViews(prescriptionId, null, null, null, null);

            // Trả JSON
            resp.getWriter().write(gson.toJson(result));
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Invalid prescriptionId\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"Internal server error: " + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            StringBuilder sb = new StringBuilder();
            String line;
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String body = sb.toString();

            // Parse JSON request
            JsonObject jsonObject = gson.fromJson(body, JsonObject.class);
            int patientId = jsonObject.get("patientId").getAsInt();
            int doctorId = jsonObject.get("doctorId").getAsInt();
            int pharmacistId = jsonObject.get("pharmacistId").getAsInt();
            JsonArray medicinesJson = jsonObject.getAsJsonArray("medicines");

            List<MedicineDetail> medicines = new ArrayList<>();
            for (JsonElement element : medicinesJson) {
                JsonObject medObj = element.getAsJsonObject();
                MedicineDetail med = new MedicineDetail();
                med.setMedicineId(medObj.get("medicineId").getAsInt());
                med.setQuantity(medObj.get("quantity").getAsInt());
                med.setDosage(medObj.get("dosage").getAsString());
                medicines.add(med);
            }

            int prescriptionId = prescriptionDAO.createPrescriptionForExistingPatient(patientId, doctorId, pharmacistId, medicines);
            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("prescriptionId", prescriptionId);
            resp.getWriter().write(gson.toJson(responseJson));
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JsonObject errorJson = new JsonObject();
            errorJson.addProperty("error", "Lỗi khi tạo đơn thuốc: " + e.getMessage());
            resp.getWriter().write(gson.toJson(errorJson));
            e.printStackTrace();
        }
    }
}
