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
public class BuildingTypeManager {
    private List<BuildingType> typeList = new ArrayList<>();
    
    public List<BuildingType> getAllBuildingType() {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("select * from tblBuildingType");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BuildingType type = new BuildingType();
                type.setId(rs.getInt("buildingType_id"));
                type.setTypeName(rs.getString("buildingType_name"));
                typeList.add(type);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return typeList;
    }
}
