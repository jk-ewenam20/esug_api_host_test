package com.sesElearning.sesElearningPlatform.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

public class courses {
    public Connection con = null;

    public String select_all_courses() {
        String result = null;
        String SQL = "SELECT * FROM public.select_all_courses()";
        Connection conn = con;
        try {

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            ResultSet response = pstmt.executeQuery();
            while (response.next()) {
                result = response.getString("select_all_courses");
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

    public String delete_courses(String _course_code) {
        String result = null;
        String SQL = "SELECT * FROM user_private.delete_course_by_code(?)";
        Connection conn = con;
        try {

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, _course_code);
            ResultSet response = pstmt.executeQuery();
            while (response.next()) {
                result = response.getString("delete_course_by_code");
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

    public String addCourse(String courseData) {
        String result = null;
        String SQL = "SELECT add_course(?::JSONB) AS course_data"; // Cast to JSON
        Connection conn = con;
        try {
            // Prepare the JSON object for the query
            JSONObject jsonObject = new JSONObject(courseData);

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, jsonObject.toString());

            // Execute the function and get the result
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("course_data");
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

}