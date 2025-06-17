package controller;

import com.google.gson.Gson;
import dao.PrescriptionViewDAO;
import modelView.PrescriptionView;

import com.google.gson.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/prescription-views")
public class PrescriptionViewServlet extends HttpServlet {
    private final PrescriptionViewDAO prescriptionViewDAO = new PrescriptionViewDAO();
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
        String patientName = req.getParameter("patientName");
        String doctorName = req.getParameter("doctorName");
        String status = req.getParameter("status");
        String sort = req.getParameter("sort"); // "asc" hoặc "desc"
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        List<PrescriptionView> list = prescriptionViewDAO.searchPrescriptionViews(
                patientName, doctorName, status, sort
        );
        resp.getWriter().write(gson.toJson(list));
    }

}
