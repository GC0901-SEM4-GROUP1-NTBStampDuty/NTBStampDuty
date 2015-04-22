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
public class BuildingManager {
    private List<Building> buidingList = new ArrayList<>();
    
    public List<Building> getAllBuilding(){
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("select * from tblBuildingDetails");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Building building = new Building();
                building.setBuildingID(rs.getInt("building_id"));
                building.setLandID(rs.getInt("land_id"));
                building.setBuidingTypeID(rs.getInt("buildingType_id"));
                building.setBuildingName(rs.getString("bulding_name"));
                building.setFloors(rs.getInt("floors"));
                building.setRooms(rs.getInt("rooms"));
                building.setHouses(rs.getInt("houses"));
                building.setShops(rs.getInt("shops"));
                building.setDateContructed(rs.getDate("date_contructed"));
                building.setCompletedPercent(rs.getInt("completed_percent"));
                buidingList.add(building);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buidingList;
    }
}
