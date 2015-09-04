/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.contract;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.contract.Contract;
import model.contract.ContractManager;
import model.project.Project;
import model.project.ProjectManager;
import model.user.User;
import model.user.UserManager;

/**
 *
 * @author Administrator
 */
public class addContract extends HttpServlet {

    private List<Contract> contractList = new ArrayList<>();

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
            out.println("<title>Servlet addContract</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addContract at " + request.getContextPath() + "</h1>");
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
        try {
            String customer = request.getParameter("customerName");
            String customerPhone = request.getParameter("customerPhone");
            String customerAddress = request.getParameter("customerAddress");
            String customerBirth = request.getParameter("customerBirth");
            String customerGender = request.getParameter("customerGender");
            String customerEmail = request.getParameter("customerEmail");
            UserManager um = new UserManager();
            um.addUser(customer, "123456", 2, customer, customerGender, customerPhone, customerBirth, customerAddress, customerEmail);

            int projectPeriod = Integer.valueOf(request.getParameter("projectPeriod"));
            int roomId = Integer.valueOf(request.getParameter("roomId"));
            java.util.Date utilDate = new java.util.Date();
            Timestamp createDate = new Timestamp(utilDate.getTime());
            int payment = Integer.valueOf(request.getParameter("total"));
            int deposit = Integer.valueOf(request.getParameter("deposit"));
            int due = Integer.valueOf(request.getParameter("due"));
            ContractManager cm = new ContractManager();
            cm.addContract(customer, roomId, createDate, projectPeriod, deposit, payment, deposit, due, 1);
            int page = 1;
            int recordsPerPage = 15;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            ContractManager manager = new ContractManager();
            contractList = manager.getAllContractByDate((page - 1) * recordsPerPage, recordsPerPage * page);
            int noOfRecords = manager.getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            ProjectManager pm = new ProjectManager();
            List<Project> proList = pm.getProjectToAddContract();
            request.setAttribute("proList", proList);
            request.setAttribute("contractList", contractList);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            RequestDispatcher rd = request.getRequestDispatcher("contract_page.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
