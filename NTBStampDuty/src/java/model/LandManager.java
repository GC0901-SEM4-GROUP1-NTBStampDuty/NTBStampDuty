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
    private int noOfRecords;
    
    public List<Land> getAllLand(int startIndex, int endIndex){
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement(
                "WITH limt_land AS\n" +
                "  ( SELECT land_id,size,address_id,building_types,building_plan,built_status,img, ROW_NUMBER() OVER (ORDER BY land_id ASC) AS [row_number]\n" +
                "    FROM tblLand\n" +
                "  )\n" +
                "SELECT land_id,size,address_id,building_types,building_plan,built_status,img FROM limt_land WHERE [row_number]>"+startIndex+" AND [row_number]<="+endIndex
            );
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
            rs.close();
            ps = conn.getConnection().prepareStatement("select count(*) from tblLand");
            rs = ps.executeQuery();
            if(rs.next())
                this.noOfRecords = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return landList;
    }
    
    public int getNoOfRecords() {
        return noOfRecords;
    }
    
}
