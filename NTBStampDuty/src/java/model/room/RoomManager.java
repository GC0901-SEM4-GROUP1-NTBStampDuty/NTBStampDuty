/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.GetConnection;

/**
 *
 * @author SonNguyen
 */
public class RoomManager {

    public List<Room> listRoom = new ArrayList<>();
    public List<Room> roomListContract = new ArrayList<>();
    private int noOfRecords;

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public List<Room> getRoomByIdType(int buildingId, int typeId, int startIndex, int endIndex) {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement(
                    "WITH limt_room AS\n"
                    + "(select room_id, tblRoomDetails.building_id as building_id, [type_id], room_size, [floor], room_price, ROW_NUMBER() OVER (ORDER BY tblRoomDetails.room_id ASC) AS [row_number]\n"
                    + "from tblRoomDetails\n"
                    + "inner join tblBuildingDetails\n"
                    + "on tblRoomDetails.building_id = tblBuildingDetails.building_id \n"
                    + ")\n"
                    + "select room_id, building_id, [type_id], room_size, [floor], room_price FROM limt_room WHERE [row_number]>" + startIndex + " AND [row_number]<=" + endIndex + " AND building_id=? AND [type_id]=?"
            );
            ps.setInt(1, buildingId);
            ps.setInt(2, typeId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setRoomId(String.valueOf(rs.getInt("room_id")));
                room.setBuildingId(rs.getInt("building_id"));
                int type = rs.getInt("type_id");
                switch (type) {
                    case 1:
                        room.setRoomType("House");
                        break;
                    case 2:
                        room.setRoomType("Shop");
                        break;
                    case 3:
                        room.setRoomType("Office");
                        break;
                    default:
                        room.setRoomType("House");
                        break;
                }
                room.setRoomSize(rs.getInt("room_size"));
                room.setRoomFloor(rs.getInt("floor"));
                room.setRoomPrice(rs.getInt("room_price"));
                listRoom.add(room);
            }
            rs.close();
            ps = conn.getConnection().prepareStatement("select count(*) from tblRoomDetails");
            rs = ps.executeQuery();
            if (rs.next()) {
                this.noOfRecords = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listRoom;
    }

    public List<Room> getRoomByProjectID(int projectId) {
        try {
            GetConnection conn = new GetConnection();
            PreparedStatement ps = conn.getConnection().prepareStatement(
                    "Select r.room_id,r.building_id,r.[type_id],room_size,r.[floor],r.room_price\n"
                    + "from tblRoomDetails r \n"
                    + "inner join tblBuildingDetails b \n"
                    + "on r.building_id = b.building_id JOIN tblProjects p\n"
                    + "on b.building_id = p.building_id"
                    + "Where p.proj_id = ? And r.sellStatus=0 \n"
            );
            ps.setInt(1, projectId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setRoomId(rs.getInt("floor") + "-" + rs.getInt("room_id"));
                room.setBuildingId(rs.getInt("building_id"));
                int type = rs.getInt("type_id");
                switch (type) {
                    case 1:
                        room.setRoomType("House");
                        break;
                    case 2:
                        room.setRoomType("Shop");
                        break;
                    case 3:
                        room.setRoomType("Office");
                        break;
                    default:
                        room.setRoomType("House");
                        break;
                }
                room.setRoomSize(rs.getInt("room_size"));
                room.setRoomFloor(rs.getInt("floor"));
                room.setRoomPrice(rs.getInt("room_price"));
                roomListContract.add(room);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomListContract;
    }
}
