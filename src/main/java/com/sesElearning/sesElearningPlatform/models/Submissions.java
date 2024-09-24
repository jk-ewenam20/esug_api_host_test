package com.sesElearning.sesElearningPlatform.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

public class Submissions {
    public Connection con = null;

    // Method to add a submission using the stored function
    public String addSubmission(String submissionData) {
        String result = null;
        String SQL = "SELECT user_private.insert_submission(?::JSON) AS submission_data"; // Cast to JSON
        Connection conn = con;
        try {
            // Prepare the JSON object for the query
            JSONObject jsonObject = new JSONObject(submissionData);
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, jsonObject.toString());

            // Execute the function and get the result
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("submission_data");
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

    // Method to grade a submission using JSON
    public String gradeSubmission(String gradingData) {
        String result = null;
        String SQL = "SELECT user_private.grade_submission(?::JSON) AS grading_result"; // Call the stored function
        Connection conn = con;
        try {
            // Prepare the JSON object for the query
            JSONObject jsonObject = new JSONObject(gradingData);

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, jsonObject.toString());

            // Execute the function and get the result
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("grading_result");
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

    // Method to select all submissions and return them as JSON
    public String getAllSubmissions() {
        String result = null;
        String SQL = "SELECT user_private.get_all_submissions() AS submission_data"; // Call the SQL function
        Connection conn = con;
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("submission_data"); // The first column contains the JSON
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
