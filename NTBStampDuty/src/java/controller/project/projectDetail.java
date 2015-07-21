/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.building.Building;
import model.building.BuildingManager;
import model.land.Land;
import model.land.LandManager;
import model.period.PeriodManager;
import model.project.Project;
import model.project.ProjectManager;
import model.User;
import model.UserManager;

/**
 *
 * @author SonNguyen
 */
public class projectDetail extends HttpServlet {

    private List<Project> projectList = new ArrayList<>();
    private List<Building> buildingList = new ArrayList<>();

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
            out.println("<title>Servlet projectDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet projectDetail at " + request.getContextPath() + "</h1>");
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
        int page = 1;
        int recordsPerPage = 10;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        ProjectManager manager = new ProjectManager();
        projectList = manager.getAllProject((page - 1) * recordsPerPage, recordsPerPage * page);
        int noOfRecords = manager.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        PeriodManager pem = new PeriodManager();
        for(int i=0; i<projectList.size(); i++){
            projectList.get(i).setCompletePercent(pem.getPeriod(projectList.get(i).getProjectID()).getPercent());
        }
        BuildingManager buildingManager = new BuildingManager();
        buildingList = buildingManager.getBuildingToAdd();
        request.setAttribute("buildingList", buildingList);
        request.setAttribute("projectList", projectList);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        RequestDispatcher rd = request.getRequestDispatcher("project_page.jsp");
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
        int page = 1;
        int recordsPerPage = 10;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        ProjectManager manager = new ProjectManager();
        projectList = manager.getAllProject((page - 1) * recordsPerPage, recordsPerPage * page);
        int noOfRecords = manager.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        PeriodManager pem = new PeriodManager();
        for(int i=0; i<projectList.size(); i++){
            projectList.get(i).setCompletePercent(pem.getPeriod(projectList.get(i).getProjectID()).getPercent());
        }
        BuildingManager buildingManager = new BuildingManager();
        buildingList = buildingManager.getBuildingToAdd();
        request.setAttribute("buildingList", buildingList);
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
