package com.sesElearning.sesElearningPlatform.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

public class course_material {
  public Connection con = null;

  public String get_course_materials(){
    String result  = null;
    String SQL = "SELECT public.get_all_materials()";
    Connection conn = con;
        try {

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            ResultSet response = pstmt.executeQuery();
            while (response.next()) {
                result = response.getString("get_all_materials");
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

    public String delete_material(String _title) {
        String result = null;
        String SQL = "SELECT * FROM user_private.delete_material_by_title(?)";
        Connection conn = con;
        try {

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, _title);
            ResultSet response = pstmt.executeQuery();
            while (response.next()) {
                result = response.getString("delete_material_by_title");
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

   public String add_course_material(String courseMaterialData) {
        String result = null;
        String SQL = "SELECT insert_materials(?::JSONB) AS course_material_data"; // Cast to JSON
        Connection conn = con;
        try {
            // Prepare the JSON object for the query
            JSONObject jsonObject = new JSONObject(courseMaterialData);

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, jsonObject.toString());

            // Execute the function and get the result
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("course_material_data");
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
