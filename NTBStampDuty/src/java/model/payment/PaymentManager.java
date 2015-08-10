/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.payment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.GetConnection;

/**
 *
 * @author SonNguyen
 */
public class PaymentManager {

    private List<Payment> listPayment;

    public List<Payment> getPaymentByContract(int contractId) {
        try {
            listPayment = new ArrayList<>();
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("Select*from tblPayment where contract_id=?");
            ps.setInt(1, contractId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Payment p = new Payment();
                p.setContractId(rs.getInt("contract_id"));
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date time = rs.getDate("payment_time");
                p.setPaymentTime(dateFormat.format(time));
                p.setpTime(time);
                p.setPaid(rs.getInt("paid"));
                listPayment.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPayment;
    }

    public List<Payment> getPaymentByCustomer(String customer) {
        try {
            listPayment = new ArrayList<>();
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("Select*from tblPayment "
                    + "inner join tblContract "
                    + "on tblPayment.contract_id = tblContract.con_id "
                    + "where tblContract.username = ?");
            ps.setString(1, customer);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Payment p = new Payment();
                p.setContractId(rs.getInt("contract_id"));
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date time = rs.getDate("payment_time");
                p.setPaymentTime(dateFormat.format(time));
                p.setpTime(time);
                p.setPaid(rs.getInt("paid"));
                listPayment.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPayment;
    }
    
    public int getTotalPaidByContract(int contractId) {
        int totalPaid = 0;
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("select sum(paid) as total from tblPayment where contract_id = ?");
            ps.setInt(1, contractId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            totalPaid = rs.getInt("total");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalPaid;
    }
}
