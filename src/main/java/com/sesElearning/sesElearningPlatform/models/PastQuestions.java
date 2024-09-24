package com.sesElearning.sesElearningPlatform.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

public class PastQuestions {
    public Connection con = null;

    // Method to add a past question using the stored function
    public String addPastQuestion(String p_question_data) {
    String result = null;
    String SQL = "SELECT public.insert_past_question(?::JSONB) AS question_data"; // Cast to JSONB
    Connection conn = con;
    try {
        // Prepare the JSON object for the query
        JSONObject jsonObject = new JSONObject(p_question_data);

        PreparedStatement pstmt = conn.prepareStatement(SQL);
        pstmt.setString(1, jsonObject.toString());

        // Execute the function and get the result
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            result = rs.getString("question_data");
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

    public String delete_past_question(String _title) {
        String result = null;
        String SQL = "SELECT * FROM user_private.delete_pq_by_title(?)";
        Connection conn = con;
        try {

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, _title);
            ResultSet response = pstmt.executeQuery();
            while (response.next()) {
                result = response.getString("delete_pq_by_title");
            }
        } catch (SQLException e) {
            // Print Errors in console.
            System.out.println(e.getMessage());
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

    // Method to select all past questions and return them as JSON
    public String getAllPastQuestions() {
        String result = null;
        String SQL = "SELECT public.get_all_past_questions() AS question_data"; // Call the sql function
        Connection conn = con;
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("question_data"); // The first column contains the JSON
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