package com.sesElearning.sesElearningPlatform.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

public class Assignments {
    public Connection con = null;

    // Method to add an assignment using the stored function
    public String addAssignment(String assignmentData) {
        String result = null;
        String SQL = "SELECT user_private.insert_assignment(?::JSON) AS assignment_data"; // Cast to JSON
        Connection conn = con;
        try {
            // Prepare the JSON object for the query
            JSONObject jsonObject = new JSONObject(assignmentData);

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, jsonObject.toString());

            // Execute the function and get the result
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("assignment_data");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            result = "Error: " + e.getMessage();
        } catch (Exception e) {
            System.out.println("Error processing JSON: " + e.getMessage());
            result = "Error processing JSON: " + e.getMessage();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return result;
    }

    // Method to select all assignments and return them as JSON
    public String getAllAssignments() {
        String result = null;
        String SQL = "SELECT user_private.get_all_assignments() AS assignment_data"; // Call the SQL function
        Connection conn = con;
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("assignment_data"); // The first column contains the JSON
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            result = "Error: " + e.getMessage(); // Return SQL error message
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return result;
    }
}