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

    private Building buildingDetails = new Building();
    private List<Building> buidingList = new ArrayList<>();
    private int noOfRecords;

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public List<Building> getAllBuilding(int startIndex, int endIndex) {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement(
                    "WITH limt_built AS\n"
                    + "  ( SELECT building_id,land_id,buildingType_id,building_name,floors,rooms,houses,shops, ROW_NUMBER() OVER (ORDER BY land_id ASC) AS [row_number]\n"
                    + "    FROM tblBuildingDetails\n"
                    + "  )\n"
                    + "SELECT building_id,land_id,buildingType_id,building_name,floors,rooms,houses,shops FROM limt_built WHERE [row_number] >" + startIndex + " AND [row_number]<=" + endIndex
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Building building = new Building();
                building.setBuildingID(rs.getInt("building_id"));
                building.setLandID(rs.getInt("land_id"));
                int status = rs.getInt("buildingType_id");
                switch (status) {
                    case 1:
                        building.setBuildingType("Official");
                        break;
                    case 2:
                        building.setBuildingType("Residental");
                        break;
                    case 3:
                        building.setBuildingType("Shopping");
                        break;
                    default:
                        building.setBuildingType("");
                        break;
                }
                building.setBuildingName(rs.getString("building_name"));
                building.setFloors(rs.getInt("floors"));
                building.setRooms(rs.getInt("rooms"));
                building.setHouses(rs.getInt("houses"));
                building.setShops(rs.getInt("shops"));
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

    public Building getBuildingDetails(int id) {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("Select * from tblBuildingDetails where building_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Building building = new Building();
                building.setBuildingID(rs.getInt("building_id"));
                building.setLandID(rs.getInt("land_id"));
                int status = rs.getInt("buildingType_id");
                switch (status) {
                    case 1:
                        building.setBuildingType("Official");
                        break;
                    case 2:
                        building.setBuildingType("Residental");
                        break;
                    case 3:
                        building.setBuildingType("Shopping");
                        break;
                    default:
                        building.setBuildingType("");
                        break;
                }
                building.setBuildingName(rs.getString("building_name"));
                building.setFloors(rs.getInt("floors"));
                building.setRooms(rs.getInt("rooms"));
                building.setHouses(rs.getInt("houses"));
                building.setShops(rs.getInt("shops"));
                buildingDetails = building;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buildingDetails;
    }

    public List<Building> SearchBuild(String searchColumn, String searchValue, int startIndex, int endIndex) {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement(
                    "WITH limt_land AS\n"
                    + "  ( SELECT building_id,land_id,buildingType_id,building_name,floors,rooms,houses,shops,date_contructed, ROW_NUMBER() OVER (ORDER BY land_id ASC) AS [row_number]\n"
                    + "    FROM tblBuildingDetails\n"
                    + "    where " + searchColumn + "= ?"
                    + "  )\n"
                    + "SELECT building_id,land_id,buildingType_id,building_name,floors,rooms,houses,shops,date_contructed FROM limt_land WHERE [row_number] >" + startIndex + " AND [row_number]<=" + endIndex
            );
            ps.setString(1, searchValue);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Building building = new Building();
                building.setBuildingID(rs.getInt("building_id"));
                building.setLandID(rs.getInt("land_id"));
                int status = rs.getInt("buildingType_id");
                switch (status) {
                    case 1:
                        building.setBuildingType("Official");
                        break;
                    case 2:
                        building.setBuildingType("Residental");
                        break;
                    case 3:
                        building.setBuildingType("Shopping");
                        break;
                    default:
                        building.setBuildingType("");
                        break;
                }
                building.setBuildingName(rs.getString("building_name"));
                building.setFloors(rs.getInt("floors"));
                building.setRooms(rs.getInt("rooms"));
                building.setHouses(rs.getInt("houses"));
                building.setShops(rs.getInt("shops"));
                buidingList.add(building);
            }
            this.noOfRecords = buidingList.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buidingList;
    }
}
