package controller;

import com.google.gson.Gson;
import dao.MedicineViewDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import modelView.MedicineView;

import java.io.IOException;

@WebServlet("/api/medicine-view")
public class MedicineViewServlet extends HttpServlet {
    private final MedicineViewDAO dao = new MedicineViewDAO();
    private final Gson gson = new Gson();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        if (idStr == null || idStr.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Missing medicine ID\"}");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            MedicineView detail = dao.getMedicineDetailById(id);
            if (detail == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("{\"error\": \"Medicine not found\"}");
            } else {
                resp.getWriter().write(gson.toJson(detail));
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Invalid ID\"}");
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"Internal server error\"}");
        }
    }
}