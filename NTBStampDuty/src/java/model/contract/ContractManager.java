/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.contract;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.GetConnection;
import model.room.Room;

/**
 *
 * @author SonNguyen
 */
public class ContractManager {

    public List<Contract> listContract;
    public Contract contract;
    private int noOfRecords;

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public List<Contract> getAllContractByDate(int startIndex, int endIndex) {
        try {
            listContract = new ArrayList<>();
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement(
                    "WITH limt_contract AS\n"
                    + "(select con_id, username, room_id, created_date, deposit, total_payment, total_paid, total_due, invoice_status, ROW_NUMBER() OVER (ORDER BY created_date DESC) AS [row_number]\n"
                    + "from tblContract\n"
                    + ")\n"
                    + "select con_id, username, room_id, created_date, deposit, total_payment, total_paid, total_due, invoice_status FROM limt_contract WHERE [row_number]>" + startIndex + " AND [row_number]<=" + endIndex
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contract c = new Contract();
                c.setContractId(rs.getInt("con_id"));
                c.setUsername(rs.getString("username"));
                c.setRoomId(rs.getInt("room_id"));
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
                listContract.add(c);
            }
            rs.close();
            ps = conn.getConnection().prepareStatement("select count(*) from tblContract");
            rs = ps.executeQuery();
            if (rs.next()) {
                this.noOfRecords = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listContract;
    }

    public Contract getContractByRoom(int roomId) {
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

    public List<Contract> getAllContractByMonthAndYear(int month, int year) {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("select * from tblContract");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getDate("created_date").getMonth() + 1 == month && rs.getDate("created_date").getYear() + 1900 == year) {
                    Contract c = new Contract();
                    c.setContractId(rs.getInt("con_id"));
                    c.setUsername(rs.getString("username"));
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    Date createDate = rs.getDate("created_date");
                    c.setCreatedDate(dateFormat.format(createDate));
                    listContract.add(c);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listContract;
    }
    
    public int getAllContractNumberByMonthAndYear(int month, int year) {
        int i = 0;
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("select * from tblContract");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getDate("created_date").getMonth() + 1 == month && rs.getDate("created_date").getYear() + 1900 == year) {
                    i = i + 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
    
    public int getAllContractNumberByMonthAndYearAndStatus(int month, int year, int status) {
        int i = 0;
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("select * from tblContract where invoice_status = ?");
            ps.setInt(1, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getDate("created_date").getMonth() + 1 == month && rs.getDate("created_date").getYear() + 1900 == year) {
                    i = i + 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }


    public List<Contract> getNewestContract() {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("select * from tblContract order by created_date desc");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contract c = new Contract();
                c.setContractId(rs.getInt("con_id"));
                c.setUsername(rs.getString("username"));
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date createDate = rs.getDate("created_date");
                c.setCreatedDate(dateFormat.format(createDate));
                listContract.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listContract;
    }

    public List<Contract> getContractByStatus(int status) {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("select * from tblContract where invoice_status = ?");
            ps.setInt(1, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contract c = new Contract();
                c.setContractId(rs.getInt("con_id"));
                c.setUsername(rs.getString("username"));
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date createDate = rs.getDate("created_date");
                c.setCreatedDate(dateFormat.format(createDate));
                listContract.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listContract;
    }

    public void addContract(String customer, int roomId, Timestamp createDate, int period, int deposit, int totalpayment, int paid, int due, int stt) {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("insert into tblContract values(?, ?, ?, ?, ?, ?,?,?,?)");
            ps.setString(1, customer);
            ps.setInt(2, roomId);
            ps.setTimestamp(3, createDate);
            ps.setInt(4, period);
            ps.setInt(5, deposit);
            ps.setInt(6, totalpayment);
            ps.setInt(7, paid);
            ps.setInt(8, due);
            ps.setInt(9, stt);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
