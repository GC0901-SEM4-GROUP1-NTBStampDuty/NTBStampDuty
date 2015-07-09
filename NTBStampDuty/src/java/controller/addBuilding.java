/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
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

/**
 *
 * @author Phuc
 */
@WebServlet(name = "addBuilding", urlPatterns = {"/addBuilding"})
@MultipartConfig
public class addBuilding extends HttpServlet {

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
            out.println("<title>Servlet addBuilding</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addBuilding at " + request.getContextPath() + "</h1>");
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
        String buildingName = request.getParameter("build_name");
        int land = Integer.valueOf(request.getParameter("landColumn"));
        int type = Integer.valueOf(request.getParameter("typeColumn"));
        int floors = Integer.valueOf(request.getParameter("floors"));
        int rooms = Integer.valueOf(request.getParameter("rooms"));
        int houses = Integer.valueOf(request.getParameter("houses"));
        
        int shops = Integer.valueOf(request.getParameter("shops"));
        Part part = request.getPart("buildImage");
        
//        String url = request.get("buildImage");
//        BufferedImage image = ImageIO.read(new File("favicon.png"));
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ImageIO.write(image, "png", baos);
//        String encodedImage = Base64.encode(baos.toByteArray());
        
        InputStream stream = part.getInputStream();
        String fileName = part.getSubmittedFileName();
        String ImagePath = "C:\\Users\\Phuc\\Desktop\\pictures\\" + fileName;
        File uploadLocation = new File(ImagePath);
        Files.copy(stream, uploadLocation.toPath());
        BuildingManager manager = new BuildingManager();
        manager.addNewBuilding(land, type, buildingName, floors, rooms, houses, shops, ImagePath);
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
