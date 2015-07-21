/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.contract;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.GetConnection;

/**
 *
 * @author SonNguyen
 */
public class ContractManager {
    public Contract contract;
    
    public Contract getContractByRoom(int roomId){
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("select*from tblContract where room_id = ?");
            ps.setInt(1, roomId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contract c = new Contract();
                c.setContractId(rs.getInt("con_id"));
                c.setUsername(rs.getString("username"));
                c.setRoomId(rs.getInt("room_id"));
                c.setPaymentId(rs.getInt("payment_id"));
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date createDate = rs.getDate("created_date");
                c.setCreatedDate(dateFormat.format(createDate));
                c.setcDate(createDate);
                c.setDeposit(rs.getInt("deposit"));
                c.setTotalPayment(rs.getInt("total_payment"));
                c.setTotalPaid(rs.getInt("total_paid"));
                c.setTotalDue(rs.getInt("total_due"));
                c.setInvoiceStatus(rs.getInt("invoice_status"));
                contract = c;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contract;
    }
}
