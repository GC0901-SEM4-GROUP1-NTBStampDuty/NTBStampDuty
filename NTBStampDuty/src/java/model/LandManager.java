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
 * @author SonNguyen
 */
public class LandManager {
    private List<Land> landList = new ArrayList<>();
    
    public List<Land> getAllLand(){
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("select * from tblLand");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Land land = new Land();
                land.setLandID(rs.getInt("land_id"));
                land.setSize(rs.getInt("size"));
                land.setAddressID(rs.getInt("address_id"));
                land.setBuildingTypes(rs.getString("building_types"));
                land.setBuildingPlan(rs.getString("building_plan"));
                land.setBuildStatus(rs.getInt("built_status"));
                land.setImg(rs.getString("img"));
                landList.add(land);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return landList;
    }
}
