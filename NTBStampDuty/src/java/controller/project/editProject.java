/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.Format;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.building.Building;
import model.building.BuildingManager;
import model.period.PeriodManager;
import model.project.Project;
import model.project.ProjectManager;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "editProject", urlPatterns = {"/editProject"})
public class editProject extends HttpServlet {

    private List<Project> projectList = new ArrayList<>();

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
            out.println("<title>Servlet editProject</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editProject at " + request.getContextPath() + "</h1>");
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
        String editID = request.getParameter("editID");
        int editId = Integer.parseInt(editID);
        ProjectManager pm = new ProjectManager();
        Project proj = pm.getProjectDetails(editId);
        BuildingManager bm = new BuildingManager();
        Building building = bm.getBuildingDetails(proj.getBuildingId());
        request.setAttribute("project", proj);
        request.setAttribute("building", building);
        RequestDispatcher rd = request.getRequestDispatcher("project_edit.jsp");
        rd.forward(request, response);
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
        int editId = Integer.parseInt(request.getParameter("editID"));
        ProjectManager pm = new ProjectManager();
        Project proj = pm.getProjectDetails(editId);
        String projName = request.getParameter("projName");
        int buildingID = proj.getBuildingId();
        int completePercent = Integer.parseInt(request.getParameter("completePercent"));
        String createdDate = request.getParameter("createdDate");
        String finishedDate = request.getParameter("finishDate");
        int period = Integer.parseInt(request.getParameter("period"));
        if (period < 3) {
            if (completePercent == 100) {
                period = period + 1;
                completePercent = 0;
            }
        } else if (period == 3) {
        }
        pm.editProject(editId, projName, buildingID, completePercent, createdDate, finishedDate, period);
        int page = 1;
        int recordsPerPage = 12;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        projectList = pm.getAllProject((page - 1) * recordsPerPage, recordsPerPage * page);
        int noOfRecords = pm.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        PeriodManager pem = new PeriodManager();
        for (int i = 0; i < projectList.size(); i++) {
            projectList.get(i).setCompletePercent(pem.getPeriod(projectList.get(i).getProjectID()).getPercent());
        }
        request.setAttribute("projectList", projectList);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        RequestDispatcher rd = request.getRequestDispatcher("project_page.jsp");
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
