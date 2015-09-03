/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.contract;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.GetConnection;

/**
 *
 * @author Phuc
 */
public class GetProjectToAddContract {
    List<AddContractProject> projectList = new ArrayList<>();

    public List<AddContractProject> getProject(int projectID) {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement(
                    "select building_name, period, created_date, finish_date, [address], complete_percent from tblProjects\n"
                    + "inner join tblBuildingDetails\n"
                    + "on tblProjects.building_id = tblBuildingDetails.building_id\n"
                    + "inner join tblLand\n"
                    + "on tblLand.land_id = tblBuildingDetails.land_id\n"
                    + "inner join tblPeriod\n"
                    + "on tblProjects.proj_id = tblPeriod.proj_id\n"
                    + "where tblProjects.proj_id = ?"
            );
            ps.setInt(1, projectID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {    
                AddContractProject projectItem = new AddContractProject();
                projectItem.setBuildingName(rs.getString("building_name"));
                projectItem.setBuildingAddress(rs.getString("address"));
                projectItem.setComplete_percent(rs.getInt("complete_percent"));
                projectItem.setCreatedDate(rs.getString("created_date"));
                projectItem.setFinishDate(rs.getString("finish_date"));
                projectItem.setPeriod(rs.getInt("period"));
                projectList.add(projectItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return projectList;
    }
}
