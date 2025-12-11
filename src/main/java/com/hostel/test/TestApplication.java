package com.hostel.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.hostel.utils.DBConnection;

public class TestApplication {
    public static void main(String[] args) {
        System.out.println("🏨 Hostel Booking System - Database Test");
        System.out.println("========================================");
        
        testDatabaseConnection();
        testAdminAccounts();
        testAvailableRooms();
        testStudentRegistration();
    }
    
    public static void testDatabaseConnection() {
        System.out.println("\n1. Testing Database Connection...");
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("✅ SUCCESS: Database connection established!");
        } catch (Exception e) {
            System.out.println("❌ FAILED: " + e.getMessage());
        }
    }
    
    public static void testAdminAccounts() {
        System.out.println("\n2. Testing Admin Accounts...");
        String query = "SELECT username, email FROM admins";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            int count = 0;
            while (rs.next()) {
                count++;
                System.out.println("✅ Admin: " + rs.getString("username") + 
                                 " | Email: " + rs.getString("email"));
            }
            System.out.println("✅ Found " + count + " admin accounts");
        } catch (Exception e) {
            System.out.println("❌ FAILED: " + e.getMessage());
        }
    }
    
    public static void testAvailableRooms() {
        System.out.println("\n3. Testing Available Rooms...");
        String query = "SELECT hostel_name, room_number, room_type, price FROM available_rooms LIMIT 5";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            int count = 0;
            while (rs.next()) {
                count++;
                System.out.println("✅ Room: " + rs.getString("hostel_name") + 
                                 " - " + rs.getString("room_number") +
                                 " | Type: " + rs.getString("room_type") +
                                 " | Price: $" + rs.getDouble("price"));
            }
            System.out.println("✅ Found " + count + " available rooms");
        } catch (Exception e) {
            System.out.println("❌ FAILED: " + e.getMessage());
        }
    }
    
    public static void testStudentRegistration() {
        System.out.println("\n4. Testing Student Table...");
        String query = "SELECT COUNT(*) as count FROM students";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            if (rs.next()) {
                System.out.println("✅ Students table ready with " + rs.getInt("count") + " records");
            }
        } catch (Exception e) {
            System.out.println("❌ FAILED: " + e.getMessage());
        }
    }
}