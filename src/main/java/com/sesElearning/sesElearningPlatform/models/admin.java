package com.sesElearning.sesElearningPlatform.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

public class admin {
    public Connection con = null;
    public String add_admin_user(String p_admin_data) {
        String result = null;
        String SQL = "SELECT * FROM user_private.create_admin(?::JSON)";
        Connection conn = con;
        try {
            // Parse the JSON request
            JSONObject jsonObject = new JSONObject(p_admin_data);

            // Extract the password
            String password = jsonObject.getString("password");

            // Hash the password
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            // Replace the plain password with the hashed password in the JSON
            jsonObject.put("password", hashedPassword);

            // Convert the modified JSON object back to a string
            String updatedJsonRequest = jsonObject.toString();

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, updatedJsonRequest);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("create_admin");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error processing JSON: " + e.getMessage());
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

    public String reset_admin_password(String adminFormData) {
        String result = null;
        String SQL = "SELECT * FROM user_private.update_admin_password(?::JSON)";
        Connection conn = con;
        try {
            // Parse the JSON request
            JSONObject jsonObject = new JSONObject(adminFormData);

            // Extract the password
            String password = jsonObject.getString("_new_password");

            // Hash the password
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            // Replace the plain password with the hashed password in the JSON
            jsonObject.put("_new_password", hashedPassword);

            // Convert the modified JSON object back to a string
            String updatedJsonRequest = jsonObject.toString();

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, updatedJsonRequest);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("update_admin_password");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error processing JSON: " + e.getMessage());
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


    public String select_admin(Long admin_id) {
        String result = null;
        String SQL = "SELECT * FROM user_private.getadmin(?)";
        Connection conn = con;
        try {

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setLong(1, admin_id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("getadmin");
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

    public String select_all_administrators() {
        String result = null;
        String SQL = "SELECT * FROM user_private.select_all_administrators()";
        Connection conn = con;
        try {

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            ResultSet response = pstmt.executeQuery();
            while (response.next()) {
                result = response.getString("select_all_administrators");
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
}
