/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Phuc
 */
public class UserManager {

    private List<User> userList = new ArrayList<>();
    private int noOfRecords;
    
    public int getNoOfRecords() {
        return noOfRecords;
    }
    
    public List<User> getAllUser(int startIndex, int endIndex) {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement(
                    "WITH limt_user AS\n"
                    + "  ( select tblUser.username, [password], [role], fullname, age, gender, phone, day_of_birth, [address], ROW_NUMBER() OVER (ORDER BY tblUser.username ASC) AS [row_number]\n"
                    + "    from tblUser\n"
                    + "inner join tblUserDetail\n"
                    + "on tblUser.username = tblUserDetail.username \n"
                    + "  )\n"
                    + "select  username, [password], [role], fullname, age, gender, phone, day_of_birth, [address] FROM limt_user WHERE [row_number]>" + startIndex + " AND [row_number]<=" + endIndex
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setFullname(rs.getString("fullname"));
                int status = rs.getInt("role");
                switch (status) {
                    case 0:
                        user.setRole("Admin");
                        break;
                    case 1:
                        user.setRole("Employee");
                        break;
                    case 2:
                        user.setRole("Customer");
                        break;
                    default:
                        user.setRole("Customer");
                        break;
                }
                user.setAge(rs.getInt("age"));
                user.setDay_of_birth(rs.getDate("day_of_birth"));
                int gender = rs.getInt("gender");
                switch (gender) {
                    case 0:
                        user.setGender("Male");
                        break;
                    case 1:
                        user.setGender("Female");
                        break;
                    default:
                        user.setGender("");
                        break;
                }
                user.setPhone(rs.getString("phone"));
                userList.add(user);
            }
            rs.close();
            ps = conn.getConnection().prepareStatement("select count(*) from tblUser");
            rs = ps.executeQuery();
            if (rs.next()) {
                this.noOfRecords = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }
}