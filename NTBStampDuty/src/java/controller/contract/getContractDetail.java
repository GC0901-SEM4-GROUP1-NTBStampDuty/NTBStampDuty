/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.contract;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.contract.Contract;
import model.contract.ContractManager;
import model.payment.Payment;
import model.payment.PaymentManager;

/**
 *
 * @author SonNguyen
 */
public class getContractDetail extends HttpServlet {

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
            out.println("<title>Servlet getContractDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet getContractDetail at " + request.getContextPath() + "</h1>");
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
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        ContractManager cm = new ContractManager();
        Contract contract = cm.getContractByRoom(roomId);
        PaymentManager pm = new PaymentManager();
        List<Payment> listPayment = pm.getPaymentByContract(contract.getContractId());
        Locale vn = new Locale("vi", "VN");
        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance(vn);
	String payment = defaultFormat.format(contract.getTotalPayment());
        String paid = defaultFormat.format(contract.getTotalPaid());
        String due = defaultFormat.format(contract.getTotalDue());
        payment = payment.substring(0, payment.length()-1);
        paid = paid.substring(0, paid.length()-1);
        due = due.substring(0, due.length()-1);
        request.setAttribute("listPayment", listPayment);
        request.setAttribute("contract", contract);
        request.setAttribute("paid", paid);
        request.setAttribute("due", due);
        request.setAttribute("payment", payment);
        RequestDispatcher rd = request.getRequestDispatcher("contract_detail.jsp");
        rd.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-sp necific error occurs
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
