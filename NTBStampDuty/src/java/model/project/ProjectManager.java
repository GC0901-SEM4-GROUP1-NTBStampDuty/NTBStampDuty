/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.GetConnection;

/**
 *
 * @author Phuc
 */
public class ProjectManager {

    private Project projectDetails = new Project();
    private List<Project> projectList = new ArrayList<>();
    private int noOfRecords;

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public List<Project> getAllProject(int startIndex, int endIndex) {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement(
                    "WITH limt_project AS\n"
                    + "(select proj_id, proj_name,tblProjects.building_id as buildingid, building_name, created_date, finish_date, period, ROW_NUMBER() OVER (ORDER BY proj_id ASC) AS [row_number]\n"
                    + "from tblProjects \n"
                    + "inner join tblBuildingDetails\n"
                    + "on tblProjects.building_id = tblBuildingDetails.building_id\n"
                    + "where available_status = 1"
                    + ")\n"
                    + "select proj_id, proj_name,buildingid, building_name, created_date, finish_date, period FROM limt_project WHERE [row_number]>" + startIndex + " AND [row_number]<=" + endIndex
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Project project = new Project();
                project.setProjectID(rs.getInt("proj_id"));
                project.setProjectName(rs.getString("proj_name"));
                project.setBuildingId(rs.getInt("buildingid"));
                project.setBuildingName(rs.getString("building_name"));
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date createdDate = rs.getDate("created_date");
                Date finishedDate = rs.getDate("finish_date");
                project.setCreatedDate(dateFormat.format(createdDate));
                project.setFinishDate(dateFormat.format(finishedDate));
                project.setPeriod(rs.getInt("period"));
                project.setcData(rs.getDate("created_date"));
                project.setfDate(rs.getDate("finish_date"));
                projectList.add(project);
            }
            rs.close();
            ps = conn.getConnection().prepareStatement("select count(*) from tblProjects");
            rs = ps.executeQuery();
            if (rs.next()) {
                this.noOfRecords = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectList;
    }

    public Project getProjectDetails(int id) {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement(
                    "select tblProjects.proj_id, proj_name,tblProjects.building_id as buildingid, building_name, created_date, finish_date, period, complete_percent\n"
                    + "from tblProjects\n"
                    + "inner join tblBuildingDetails\n"
                    + "on tblProjects.building_id = tblBuildingDetails.building_id\n"
                    + "inner join tblPeriod\n"
                    + "on tblPeriod.proj_id = tblProjects.proj_id "
                    + "where tblProjects.proj_id = ?"
            );
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Project project = new Project();
                project.setProjectID(rs.getInt("proj_id"));
                project.setProjectName(rs.getString("proj_name"));
                project.setBuildingId(rs.getInt("buildingid"));
                project.setBuildingName(rs.getString("building_name"));
                //project.setCompletePercent(rs.getInt("complete_percent"));
                Date createdDate = rs.getDate("created_date");
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                project.setCreatedDate(dateFormat.format(createdDate));
                Date finishedDate = rs.getDate("finish_date");
                project.setFinishDate(dateFormat.format(finishedDate));
                project.setPeriod(rs.getInt("period"));
                projectDetails = project;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectDetails;
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

    public void editProject(int projectId, String projectName, int buildingID, int completePercent, String createdDate, String finishedDate, int period) {
        try {
            GetConnection conn = new GetConnection();
//            PreparedStatement ps = conn.getConnection().prepareStatement("Update tblProjects Set proj_name=?,building_id=?,complete_percent=?,created_date=CONVERT(datetime, ?, 103),finish_date=CONVERT(datetime, ?, 103),period=? where proj_id=? ");
//            ps.setString(1, projectName);
//            ps.setInt(2, buildingID);
//            ps.setInt(3, completePercent);
//            ps.setString(4, createdDate);
//            ps.setString(5, finishedDate);
//            ps.setInt(6, period);
//            ps.setInt(7, projectId);
//            ps.executeUpdate();
            PreparedStatement ps = conn.getConnection().prepareStatement("update tblPeriod\n"
                    + "set complete_percent = ?\n"
                    + "where proj_id = ?");
            ps.setInt(1, completePercent);
            ps.setInt(2, projectId);
            ps.executeUpdate();
            PreparedStatement ps2 = conn.getConnection().prepareStatement("update tblProjects\n"
                    + "set period = ?\n"
                    + "where proj_id = ?");
            ps2.setInt(1, period);
            ps2.setInt(2, projectId);
            ps2.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Project> getProjectByDate(int startIndex, int endIndex) {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement(
                    "WITH limt_project AS\n"
                    + "(select proj_id, proj_name,tblProjects.building_id as buildingid, building_name, created_date, finish_date, period, ROW_NUMBER() OVER (ORDER BY created_date desc) AS [row_number]\n"
                    + "from tblProjects\n"
                    + "inner join tblBuildingDetails\n"
                    + "on tblProjects.building_id = tblBuildingDetails.building_id\n"
                    + "where available_status = 1"
                    + ")\n"
                    + "select proj_id, proj_name,buildingid, building_name, created_date, finish_date, period FROM limt_project WHERE [row_number]>" + startIndex + " AND [row_number]<=" + endIndex
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Project project = new Project();
                project.setProjectID(rs.getInt("proj_id"));
                project.setProjectName(rs.getString("proj_name"));
                project.setBuildingId(rs.getInt("buildingid"));
                project.setBuildingName(rs.getString("building_name"));
                //project.setCompletePercent(rs.getInt("complete_percent"));
                Date createdDate = rs.getDate("created_date");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                project.setCreatedDate(dateFormat.format(createdDate));
                Date finishedDate = rs.getDate("finish_date");
                project.setFinishDate(dateFormat.format(finishedDate));
                project.setPeriod(rs.getInt("period"));
                projectList.add(project);
            }
            rs.close();
            ps = conn.getConnection().prepareStatement("select count(*) from tblProjects");
            rs = ps.executeQuery();
            if (rs.next()) {
                this.noOfRecords = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectList;
    }

    public List<Project> getProjectByName(int startIndex, int endIndex) {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement(
                    "WITH limt_project AS\n"
                    + "(select proj_id, proj_name,tblProjects.building_id as buildingid, building_name, created_date, finish_date, period, ROW_NUMBER() OVER (ORDER BY proj_name ASC) AS [row_number]\n"
                    + "from tblProjects\n"
                    + "inner join tblBuildingDetails\n"
                    + "on tblProjects.building_id = tblBuildingDetails.building_id\n"
                    + "where available_status = 1"
                    + ")\n"
                    + "select proj_id, proj_name,buildingid, building_name, created_date, finish_date, period FROM limt_project WHERE [row_number]>" + startIndex + " AND [row_number]<=" + endIndex
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Project project = new Project();
                project.setProjectID(rs.getInt("proj_id"));
                project.setProjectName(rs.getString("proj_name"));
                project.setBuildingId(rs.getInt("buildingid"));
                project.setBuildingName(rs.getString("building_name"));
                //project.setCompletePercent(rs.getInt("complete_percent"));
                Date createdDate = rs.getDate("created_date");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                project.setCreatedDate(dateFormat.format(createdDate));
                Date finishedDate = rs.getDate("finish_date");
                project.setFinishDate(dateFormat.format(finishedDate));
                project.setPeriod(rs.getInt("period"));
                projectList.add(project);
            }
            rs.close();
            ps = conn.getConnection().prepareStatement("select count(*) from tblProjects");
            rs = ps.executeQuery();
            if (rs.next()) {
                this.noOfRecords = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectList;
    }

    public List<Project> getProjectByStatus(String fileterType, int startIndex, int endIndex) {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement(
                    "WITH limt_project AS\n"
                    + "(select proj_id, proj_name,tblProjects.building_id as buildingid, building_name, complete_percent, created_date, finish_date, period, ROW_NUMBER() OVER (ORDER BY proj_id ASC) AS [row_number]\n"
                    + "from tblProjects\n"
                    + "inner join tblBuildingDetails\n"
                    + "on tblProjects.building_id = tblBuildingDetails.building_id\n"
                    + "where available_status = 1 and complete_percent ? ?"
                    + ")\n"
                    + "select proj_id, proj_name,buildingid, building_name, complete_percent, created_date, finish_date, period FROM limt_project WHERE [row_number]>" + startIndex + " AND [row_number]<=" + endIndex
            );
            switch (fileterType) {
                case "notBuilt":
                    ps.setString(1, "=");
                    ps.setString(2, "0");
                    break;
                case "beingBuilt":
                    ps.setString(1, ">");
                    ps.setString(2, "0");
                    break;
                case "built":
                    ps.setString(1, "=");
                    ps.setString(2, "100");
                    break;
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Project project = new Project();
                project.setProjectID(rs.getInt("proj_id"));
                project.setProjectName(rs.getString("proj_name"));
                project.setBuildingId(rs.getInt("buildingid"));
                project.setBuildingName(rs.getString("building_name"));
                //project.setCompletePercent(rs.getInt("complete_percent"));
                Date createdDate = rs.getDate("created_date");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                project.setCreatedDate(dateFormat.format(createdDate));
                Date finishedDate = rs.getDate("finish_date");
                project.setFinishDate(dateFormat.format(finishedDate));
                project.setPeriod(rs.getInt("period"));
                projectList.add(project);
            }
            rs.close();
            ps = conn.getConnection().prepareStatement("select count(*) from tblProjects");
            rs = ps.executeQuery();
            if (rs.next()) {
                this.noOfRecords = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectList;
    }

    public void addProject(String proName, int buildingID, Timestamp createdDate, Timestamp finishDate) {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement("insert into tblProjects values(?, ?, ?, ?, ?, ?)");
            ps.setString(1, proName);
            ps.setInt(2, buildingID);
            ps.setTimestamp(3, createdDate);
            ps.setTimestamp(4, finishDate);
            ps.setInt(5, 1);
            ps.setInt(6, 1);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Project> getProjectToAddContract() {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement(
                    "select * from tblProjects"
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Project project = new Project();
                project.setProjectID(rs.getInt("proj_id"));
                project.setProjectName(rs.getString("proj_name"));                
                projectList.add(project);
            }
            rs.close();
            ps = conn.getConnection().prepareStatement("select count(*) from tblProjects");
            rs = ps.executeQuery();
            if (rs.next()) {
                this.noOfRecords = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectList;
    }
}
