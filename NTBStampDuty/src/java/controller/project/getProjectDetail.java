/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.building.Building;
import model.building.BuildingManager;
import model.period.CompareDate;
import model.period.Period;
import model.period.PeriodManager;
import model.project.Project;
import model.project.ProjectManager;

/**
 *
 * @author Phuc
 */
@WebServlet(name = "getProjectDetail", urlPatterns = {"/getProjectDetail"})
public class getProjectDetail extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet getProjectDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet getProjectDetail at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String projectId = request.getParameter("projectID");
        int id = Integer.parseInt(projectId);
        ProjectManager pm = new ProjectManager();
        Project proj = pm.getProjectDetails(id);
        PeriodManager pem = new PeriodManager();
        Period  period = pem.getPeriod(id);
        CompareDate cd = new CompareDate();
        Date date = new Date();
        int total_percent = 0;
        int status = 0, flat = 0;
        switch(proj.getPeriod()){
            case 1:
                total_percent = period.getPercent()/3;
                flat = cd.compareDate(date, period.getP1());
                status = cd.compareDataPercent(flat, period.getPercent(), 100);
                break;
            case 2:
                total_percent = 34+period.getPercent()/3;
                flat = cd.compareDate(date, period.getP2());
                status = cd.compareDataPercent(flat, period.getPercent(), 100);
                break;
            case 3:
                total_percent = 67+period.getPercent()/3;
                flat = cd.compareDate(date, period.getP3());
                status = cd.compareDataPercent(flat, period.getPercent(), 100);
                break;
        }
        BuildingManager bm = new BuildingManager();
        Building building = bm.getBuildingDetails(proj.getBuildingId());
        request.setAttribute("status", status);
        request.setAttribute("total_percent", total_percent);
        request.setAttribute("project", proj);
        request.setAttribute("period", period);
        request.setAttribute("building", building);
        RequestDispatcher rd = request.getRequestDispatcher("project_detail.jsp");
        rd.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
