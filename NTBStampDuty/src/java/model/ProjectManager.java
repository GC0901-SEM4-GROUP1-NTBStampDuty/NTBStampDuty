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
public class ProjectManager {
    
    private List<Project> projectList = new ArrayList<>();
    
    public List<Project> getAlllProject() {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("select * from tblProjects");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Project project = new Project();
                project.setProjectID(rs.getInt("proj_id"));
                project.setProjectName(rs.getString("proj_name"));
                project.setBuildingID(rs.getInt("building_id"));
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
}
