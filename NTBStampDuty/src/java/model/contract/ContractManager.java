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
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
    
    public void generateAndSendEmail(String email) {
        Properties mailServerProperties;
        Session getMailSession;
        MimeMessage generateMailMessage;
        try {
            mailServerProperties = System.getProperties();
            mailServerProperties.put("mail.smtp.port", "587");
            mailServerProperties.put("mail.smtp.auth", "true");
            mailServerProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            getMailSession = Session.getDefaultInstance(mailServerProperties, null);
            generateMailMessage = new MimeMessage(getMailSession);
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            generateMailMessage.setSubject("Inform about purchasing deadline");
            String emailBody = "<center><h2>NTB Stamp Duty Important Notice</h2>"
                    + "<h4><i><b>8 Ton That Thuyet, Cau Giay District, Ha Noi</b></i></h4>"
                    + "<h1>Informing about payment deadline</h1></center>"
                    + "<p>We would like to inform you that payment deadline is today. Please make the payment for due date today. Again thank you for making contract with us. If you have any problem, please contact us at ntbstampduty@fpt.com</p>"
                    + "<p>Best Regards, <p>"
                    + "<p>NTB Stamp Duty ™<p>";
            generateMailMessage.setContent(emailBody, "text/html");

            Transport transport = getMailSession.getTransport("smtp");
            transport.connect("smtp.gmail.com", "gmail", "password");
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
