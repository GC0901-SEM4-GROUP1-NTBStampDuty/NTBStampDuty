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
    private List<Location> locationList = new ArrayList<>();
    private int noOfRecords;

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public List<Land> getAllLand(int startIndex, int endIndex) {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement(
                    "WITH limt_land AS\n"
                    + "  ( select land_id, size, name, buildingType_name, building_plan, built_status, img, price, ROW_NUMBER() OVER (ORDER BY land_id ASC) AS [row_number]\n"
                    + "    from tblLand\n"
                    + "inner join tblLocation\n"
                    + "on tblLand.address_id = tblLocation.address_id\n"
                    + "inner join tblBuildingType\n"
                    + "on tblLand.building_types = tblBuildingType.buildingType_id\n"
                    + "  )\n"
                    + "select land_id, size, name, buildingType_name, building_plan, built_status, img, price FROM limt_land WHERE [row_number]>" + startIndex + " AND [row_number]<=" + endIndex
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Land land = new Land();
                land.setLandID(rs.getInt("land_id"));
                land.setSize(rs.getInt("size"));
                land.setAddressID(rs.getString("name"));
                land.setBuildingTypes(rs.getString("buildingType_name"));
                land.setBuildingPlan(rs.getString("building_plan"));
                int status = rs.getInt("built_status");
                if (status == 0) {
                    land.setBuildStatus("Not Build");
                } else if (status == 1) {
                    land.setBuildStatus("Building");
                } else {
                    land.setBuildStatus("Built");
                }
                land.setPrice(rs.getInt("price"));
                land.setImg(rs.getString("img"));
                landList.add(land);
            }
            rs.close();
            ps = conn.getConnection().prepareStatement("select count(*) from tblLand");
            rs = ps.executeQuery();
            if (rs.next()) {
                this.noOfRecords = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return landList;
    }

    public List<Land> SearchLand(String searchColumn, String searchValue, int startIndex, int endIndex) {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement(
                    "WITH limt_land AS\n"
                    + "  ( select land_id, size, name, building_types, building_plan, built_status, img, price, ROW_NUMBER() OVER (ORDER BY land_id ASC) AS [row_number]\n"
                    + "    from tblLand\n"
                    + "inner join tblLocation\n"
                    + "on tblLand.address_id = tblLocation.address_id\n"
                    + "where " + searchColumn + "= ?"
                    + "  )\n"
                    + "select land_id, size, name, building_types, building_plan, built_status, img, price FROM limt_land WHERE [row_number]>" + startIndex + " AND [row_number]<=" + endIndex
            );
            ps.setString(1, searchValue);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Land land = new Land();
                land.setLandID(rs.getInt("land_id"));
                land.setSize(rs.getInt("size"));
                land.setAddressID(rs.getString("name"));
                land.setBuildingTypes(rs.getString("building_types"));
                land.setBuildingPlan(rs.getString("building_plan"));
                int status = rs.getInt("built_status");
                if (status == 0) {
                    land.setBuildStatus("Not Build");
                } else if (status == 1) {
                    land.setBuildStatus("Building");
                } else {
                    land.setBuildStatus("Built");
                }
                land.setPrice(rs.getInt("price"));
                land.setImg(rs.getString("img"));
                landList.add(land);
            }
            this.noOfRecords = landList.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return landList;
    }

    public List<Location> getLocation() {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("select * from tblLocation");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Location location = new Location();
                location.setAddressID(rs.getInt("address_id"));
                location.setAddressName(rs.getString("name"));
                location.setPrice(rs.getInt("price"));
                locationList.add(location);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return locationList;
    }

    public List<Land> getAllLandToAdd() {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("select land_id, name\n"
                    + "from tblLand \n"
                    + "inner join tblLocation\n"
                    + "on tblLand.address_id = tblLocation.address_id\n"
                    + "inner join tblBuildingType\n"
                    + "on tblLand.building_types = tblBuildingType.buildingType_id");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Land land = new Land();
                land.setLandID(rs.getInt("land_id"));
                land.setAddressID(rs.getString("name"));
                landList.add(land);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return landList;
    }

    public void addNewLand(String name, int size, int addressId, int buildingType, String img) {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("insert into tblLand values(?,?,?,?,?)");
            ps.setString(1, name);
            ps.setInt(2, size);
            ps.setInt(3, addressId);
            ps.setInt(4, buildingType);
            ps.setString(5, img);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
