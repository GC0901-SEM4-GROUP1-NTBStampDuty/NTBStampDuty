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
    private List<BuildingDetail> buildingDetailList = new ArrayList<>();
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
                building.setTotalRoom(rs.getInt("rooms") + rs.getInt("houses") + rs.getInt("shops"));
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
    
    public List<Building> getBuildingToAdd() {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("select * from tblBuildingDetails where chosen_status = 0");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Building building = new Building();
                building.setBuildingID(rs.getInt("building_id"));
                building.setBuildingName(rs.getString("building_name"));
                buidingList.add(building);
            }
            rs.close();
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

    public void addNewBuilding(int landID, int buildingType, String buildingName, int floors, int rooms, int houses, int shops, String img) {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("insert into tblBuildingDetails values (?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, landID);
            ps.setInt(2, buildingType);
            ps.setString(3, buildingName);
            ps.setInt(4, floors);
            ps.setInt(5, rooms);
            ps.setInt(6, houses);
            ps.setInt(7, shops);
            ps.setString(8, img);
            ps.setInt(9, 0);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<BuildingDetail> getBuildingDetailDialog(int id) {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("select (select name from tblLand inner join tblLocation on tblLand.address_id = tblLocation.address_id where tblLand.land_id = tblBuildingDetails.land_id) as land, buildingType_name, building_name, floors, rooms, houses, shops, img, chosen_status from tblBuildingDetails\n"
                    + "inner join tblBuildingType\n"
                    + "on tblBuildingDetails.buildingType_id = tblBuildingType.buildingType_id\n"
                    + "where tblBuildingDetails.building_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String landName = rs.getString("land");
                String buildingType = rs.getString("buildingType_name");
                String buildingName = rs.getString("building_name");
                int floors = rs.getInt("floors");
                int rooms = rs.getInt("rooms");
                int houses = rs.getInt("houses");
                int shops = rs.getInt("shops");
                String img = rs.getString("img");
                int chosen_status = rs.getInt("chosen_status");
                BuildingDetail buildingDetail = new BuildingDetail(landName, buildingType, buildingName, floors, rooms, houses, shops, id, chosen_status);
                buildingDetailList.add(buildingDetail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buildingDetailList;
    }
    
    public void editBuilding(int landID, int buildingType, String buildingName, int floors, int rooms, int houses, int shops, String img, int id){
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("Update tblBuildingDetails Set"
                    + "land_id=?, buildingType_id=?, building_name=?, floors=?, rooms=?, houses=?, shops=?, img=?, chosen_status=?"
                    + "where building_id=?");
            ps.setInt(1, landID);
            ps.setInt(2, buildingType);
            ps.setString(3, buildingName);
            ps.setInt(4, floors);
            ps.setInt(5, rooms);
            ps.setInt(6, houses);
            ps.setInt(7, shops);
            ps.setString(8, img);
            ps.setInt(9, 0);
            ps.setInt(10, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
