/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author SonNguyen
 */
public class PeriodManager {

    private Period period;

    public Period getPeriod(int id) {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("Select*from tblPeriod where proj_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date period1 = rs.getDate("period_1");
                Date period2 = rs.getDate("period_2");
                Date period3 = rs.getDate("period_3");
                Period p = new Period();
                p.setProject_id(rs.getInt("proj_id"));
                p.setPeriod1(dateFormat.format(period1));
                p.setPeriod2(dateFormat.format(period2));
                p.setPeriod3(dateFormat.format(period3));
                p.setPercent(rs.getInt("complete_percent"));
                p.setP1(rs.getDate("period_1"));
                p.setP2(rs.getDate("period_2"));
                p.setP3(rs.getDate("period_3"));
                period = p;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return period;
    }
}
