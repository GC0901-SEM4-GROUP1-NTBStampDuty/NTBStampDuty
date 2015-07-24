/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.project;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.building.Building;
import model.building.BuildingManager;
import model.project.Project;
import model.project.ProjectManager;

/**
 *
 * @author Phuc
 */
@WebServlet(name = "addProject", urlPatterns = {"/addProject"})
public class addProject extends HttpServlet {

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
        try {
            int page = 1;
            int recordsPerPage = 12;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            String proName = request.getParameter("proName");
            int buildingID = Integer.valueOf(request.getParameter("builidingID"));
            String finishDate = request.getParameter("finishDate");
            Date d = new SimpleDateFormat("yyyy-MM-dd").parse(finishDate);
            java.util.Date utilDate = new java.util.Date();
            Timestamp createDate = new Timestamp(utilDate.getTime());
            ProjectManager manager = new ProjectManager();
            manager.addProject(proName, buildingID, createDate, new Timestamp(d.getTime()));
            BuildingManager buildingManager = new BuildingManager();
            projectList = manager.getAllProject((page - 1) * recordsPerPage, recordsPerPage * page);
            buildingList = buildingManager.getBuildingToAdd();
            int noOfRecords = manager.getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            request.setAttribute("buildingList", buildingList);
            request.setAttribute("projectList", projectList);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            RequestDispatcher rd = request.getRequestDispatcher("project_page.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
        processRequest(request, response);
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
