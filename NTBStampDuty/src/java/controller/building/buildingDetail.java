/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.building;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.building.Building;
import model.building.BuildingManager;
import model.building.BuildingType;
import model.building.BuildingTypeManager;
import model.land.Land;
import model.land.LandManager;


/**
 *
 * @author Phuc
 */
@WebServlet(name = "buildingDetail", urlPatterns = {"/buildingDetail"})
public class buildingDetail extends HttpServlet {

    private List<Building> buildingList = new ArrayList<>();
    private List<Land> landList = new ArrayList<>();
    private List<BuildingType> typeList = new ArrayList<>();


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
            out.println("<title>Servlet buildingDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet buildingDetail at " + request.getContextPath() + "</h1>");
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
        int recordsPerPage = 15;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        BuildingManager manager = new BuildingManager();
        buildingList = manager.getAllBuilding((page - 1) * recordsPerPage, recordsPerPage * page);
        int noOfRecords = manager.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        LandManager landManager = new LandManager();
        landList = landManager.getAllLandToAdd();
        BuildingTypeManager typeManager = new BuildingTypeManager();
        typeList = typeManager.getAllBuildingType();        
        request.setAttribute("landList", landList);       
        request.setAttribute("typeList", typeList);
        request.setAttribute("buildingList", buildingList);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        RequestDispatcher rd = request.getRequestDispatcher("building_page.jsp");
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
        int recordsPerPage = 15;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        BuildingManager manager = new BuildingManager();
        buildingList = manager.getAllBuilding((page - 1) * recordsPerPage, recordsPerPage * page);
        int noOfRecords = manager.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        LandManager landManger = new LandManager();
        landList = landManger.getAllLandToAdd();
        BuildingTypeManager typeManager = new BuildingTypeManager();
        typeList = typeManager.getAllBuildingType();
        request.setAttribute("landList", landList);
        request.setAttribute("typeList", typeList);
        request.setAttribute("buildingList", buildingList);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        RequestDispatcher rd = request.getRequestDispatcher("building_page.jsp");
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
