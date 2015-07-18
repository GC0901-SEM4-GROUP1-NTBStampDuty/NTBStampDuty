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
    private List<Land> landDetailList = new ArrayList<>();

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public List<Land> getAllLand(int startIndex, int endIndex) {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement(
                    "WITH limt_land AS\n"
                    + "  ( select land_id, size, address, price, building_types, img, available_status, ROW_NUMBER() OVER (ORDER BY land_id ASC) AS [row_number]\n"
                    + "    from tblLand\n"
                    + "inner join tblBuildingType\n"
                    + "on tblLand.building_types = tblBuildingType.buildingType_id\n"
                    + "  )\n"
                    + "select land_id, size, address, price, building_types, img, available_status FROM limt_land WHERE [row_number]>" + startIndex + " AND [row_number]<=" + endIndex
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Land land = new Land();
                land.setLandID(rs.getInt("land_id"));
                land.setSize(rs.getInt("size"));
                land.setAddress(rs.getString("address"));
                land.setBuildingTypes(rs.getInt("building_types"));
                land.setAvailable_status(rs.getInt("available_status"));
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
                    + "  ( select land_id, size, address, price, building_types, img, available_status, ROW_NUMBER() OVER (ORDER BY land_id ASC) AS [row_number]\n"
                    + "    from tblLand\n"
                    + "where " + searchColumn + "= ?"
                    + "  )\n"
                    + "select land_id, size, address, price, building_types, img, available_status FROM limt_land WHERE [row_number]>" + startIndex + " AND [row_number]<=" + endIndex
            );
            ps.setString(1, searchValue);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Land land = new Land();
                land.setLandID(rs.getInt("land_id"));
                land.setSize(rs.getInt("size"));
                land.setAddress(rs.getString("address"));
                land.setBuildingTypes(rs.getInt("building_types"));
                land.setAvailable_status(rs.getInt("available_status"));
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

    public List<Land> getAllLandToAdd() {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("select land_id, address\n"
                    + "from tblLand \n"
                    + "inner join tblBuildingType\n"
                    + "on tblLand.building_types = tblBuildingType.buildingType_id");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Land land = new Land();
                land.setLandID(rs.getInt("land_id"));
                land.setAddress(rs.getString("address"));
                landList.add(land);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return landList;
    }

    public void addNewLand(int size, String address, int price, int buildingTypesID, String img, int available_status) {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("insert into tblLand values(?,?,?,?,?,?)");
            ps.setInt(1, size);
            ps.setString(2, address);
            ps.setInt(3, price);
            ps.setInt(4, buildingTypesID);
            ps.setString(5, img);
            ps.setInt(6, available_status);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editLand(int landID,int size, String address, int price, int buildingTypesID, String img, int available_status) {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("Update tblLand Set size=?,address=?,price=?,building_types=?,img=?,available_status=? Where land_id=?");
            ps.setInt(1, size);
            ps.setString(2, address);
            ps.setInt(3, price);
            ps.setInt(4, buildingTypesID);
            ps.setString(5, img);
            ps.setInt(6, available_status);
            ps.setInt(7, landID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Land> getLandDetailDialog(int id) {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("select * from tblLand where land_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int size = rs.getInt("size");
                int buildingTypeID = rs.getInt("building_types");
                String address = rs.getString("address");
                int price = rs.getInt("price");
                String img = rs.getString("img");
                int available_status = rs.getInt("available_status");
                Land land = new Land(id, size, address, price, buildingTypeID, img, available_status);
                landDetailList.add(land);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return landDetailList;
    }
}
