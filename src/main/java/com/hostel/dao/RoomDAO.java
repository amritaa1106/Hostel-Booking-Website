package com.hostel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hostel.model.Room;
import com.hostel.utils.DBConnection;

public class RoomDAO {
    private Connection conn;

    public RoomDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Room> getAllAvailableRooms() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms WHERE status = 'Available'";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Room room = extractRoom(rs);
            rooms.add(room);
        }
        rs.close();
        ps.close();
        return rooms;
    }

    public Room getRoomById(int roomId) throws SQLException {
        String sql = "SELECT * FROM rooms WHERE room_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, roomId);
        ResultSet rs = ps.executeQuery();
        Room room = null;
        if (rs.next()) {
            room = extractRoom(rs);
        }
        rs.close();
        ps.close();
        return room;
    }

    public Room getRoomByNumber(String roomNumber) throws SQLException {
        String sql = "SELECT * FROM rooms WHERE room_number = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, roomNumber);
        ResultSet rs = ps.executeQuery();
        Room room = null;
        if (rs.next()) {
            room = extractRoom(rs);
        }
        rs.close();
        ps.close();
        return room;
    }

    public List<Room> getAllRooms() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Room room = extractRoom(rs);
            rooms.add(room);
        }
        rs.close();
        ps.close();
        return rooms;
    }
    public boolean roomExists(int roomId) {
    String sql = "SELECT room_id FROM rooms WHERE room_id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, roomId);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
    }


    private Room extractRoom(ResultSet rs) throws SQLException {
        Room room = new Room();
        room.setRoomId(rs.getInt("room_id"));
        room.setHostelId(rs.getInt("hostel_id"));
        room.setRoomNumber(rs.getString("room_number"));
        room.setFloor(rs.getInt("floor"));
        room.setRoomType(rs.getString("room_type"));
        room.setCapacity(rs.getInt("capacity"));
        room.setOccupiedBeds(rs.getInt("occupied_beds"));
        room.setPrice(rs.getBigDecimal("price"));
        room.setStatus(rs.getString("status"));
        room.setAmenities(rs.getString("amenities"));
        room.setHostelName(rs.getString("hostel_name"));
        room.setHostelType(rs.getString("hostel_type"));
        room.setImageUrl(rs.getString("image_url"));
        return room;
    }
}
