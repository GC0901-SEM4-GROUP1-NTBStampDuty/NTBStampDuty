/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Building;
import model.BuildingManager;
import model.BuildingType;
import model.BuildingTypeManager;
import model.Land;
import model.LandManager;
import sun.misc.BASE64Encoder;

/**
 *
 * @author SonNguyen
 */
@MultipartConfig
public class editBuilding extends HttpServlet {

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
            out.println("<title>Servlet editBuilding</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editBuilding at " + request.getContextPath() + "</h1>");
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
        String buildingName = request.getParameter("buildingName");
        int land = Integer.valueOf(request.getParameter("landColumn"));
        int type = Integer.valueOf(request.getParameter("typeColumn"));
        int floors = Integer.valueOf(request.getParameter("floors"));
        int rooms = Integer.valueOf(request.getParameter("rooms"));
        int houses = Integer.valueOf(request.getParameter("houses"));
        int buildingID = Integer.valueOf(request.getParameter("buildingID"));

        int shops = Integer.valueOf(request.getParameter("shops"));
        Part part = request.getPart("buildImage");
        InputStream stream = part.getInputStream();
        BufferedImage imageBuffer = ImageIO.read(stream);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write(imageBuffer, "jpg", output);
        byte[] imageBytes = output.toByteArray();
        BASE64Encoder encoder = new BASE64Encoder();
        String imageString = encoder.encode(imageBytes);
        output.close();

        BuildingManager manager = new BuildingManager();
        manager.editBuilding(land, type, buildingName, floors, rooms, houses, shops, imageString, buildingID);
        int page = 1;
        int recordsPerPage = 15;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
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
