/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Phuc
 */
public class ProjectManager {

    private List<Project> projectList = new ArrayList<>();

    public List<Project> getAlllProject() {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("select proj_id, proj_name, building_name, complete_percent, created_date, finish_date, period\n"
                    + "from tblProjects \n"
                    + "inner join tblBuildingDetails\n"
                    + "on tblProjects.building_id = tblBuildingDetails.building_id");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Project project = new Project();
                project.setProjectID(rs.getInt("proj_id"));
                project.setProjectName(rs.getString("proj_name"));
                project.setBuildingName(rs.getString("building_name"));
                project.setCompletePercent(rs.getInt("complete_percent"));
                project.setCreatedDate(rs.getDate("created_date"));
                project.setFinishDate(rs.getDate("finish_date"));
                project.setPeriod(rs.getInt("period"));
                projectList.add(project);
            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectList;
    }

    public void insertProject(String projectName, int buildingID, int completePercent, Timestamp createdDate, Timestamp finishedDate, int period) {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("insert into tblProjects values(?, ?, ?, ?, ?, ?)");
            ps.setString(1, projectName);
            ps.setInt(2, buildingID);
            ps.setInt(3, completePercent);
            ps.setTimestamp(4, createdDate);
            ps.setTimestamp(5, finishedDate);
            ps.setInt(6, period);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
