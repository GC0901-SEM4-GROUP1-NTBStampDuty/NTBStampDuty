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
    private int noOfRecords;

    public List<Building> getAllBuilding(int startIndex, int endIndex) {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement(
                    "WITH limt_land AS\n"
                    + "  ( SELECT building_id,land_id,buildingType_id,bulding_name,floors,rooms,houses,shops,date_contructed,completed_percent, ROW_NUMBER() OVER (ORDER BY land_id ASC) AS [row_number]\n"
                    + "    FROM tblBuildingDetails\n"
                    + "  )\n"
                    + "SELECT building_id,land_id,buildingType_id,bulding_name,floors,rooms,houses,shops,date_contructed,completed_percent FROM limt_land WHERE [row_number] >" + startIndex + " AND [row_number]<=" + endIndex
            );
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
            rs.close();
            ps = conn.getConnection().prepareStatement("select count(*) from tblBuildingDetails");
            rs = ps.executeQuery();
            if (rs.next()) {
                this.noOfRecords = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buidingList;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }
}
