package com.sesElearning.sesElearningPlatform.models;

import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Service
public class students {
    public Connection con = null;

    public String add_student(String p_student_data) {
        String result = null;
        String SQL = "SELECT * FROM user_private.add_student_user(?::JSON)";
        Connection conn = con;
        try {
            // Parse the JSON request
            JSONObject jsonObject = new JSONObject(p_student_data);

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
                result = rs.getString("add_student_user");
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

    public String edit_student_password(String studentFormData) {
        String result = null;
        String SQL = "SELECT * FROM user_private.update_student_password(?::JSON)";
        Connection conn = con;
        try {
            // Parse the JSON request
            JSONObject jsonObject = new JSONObject(studentFormData);

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
                result = rs.getString("update_student_password");
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

    public String select_all_students() {
        String result = null;
        String SQL = "SELECT * FROM user_private.select_all_students()";
        Connection conn = con;
        try {

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            ResultSet response = pstmt.executeQuery();
            while (response.next()) {
                result = response.getString("select_all_students");
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

    public String select_student_by_id(Long student_id) {
        String result = null;
        String SQL = "SELECT * FROM user_private.get_student_by_id(?)";
        Connection conn = con;
        try {

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setLong(1, student_id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("get_student_by_id");
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

    // Authenicate Student
    public String authenticate_student(Long student_id, String password) {
        String result = null;
        String SQL = "SELECT user_private.authenticate_user(?, ?)";
        Connection conn = con;

        try{
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setLong(1, student_id);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                String jsonResult = rs.getString(1);
                JSONObject authResult = new JSONObject(jsonResult);

                if (authResult.getString("status").equals("pending")) {
                    String storedHash = authResult.getString("stored_hash");
                    if (BCrypt.checkpw(password, storedHash)) {
                        authResult.put("status", "success");
                        authResult.remove("stored_hash");
                    } else {
                        authResult.put("status", "error");
                        authResult.put("message", "Invalid credentials");
                        authResult.remove("stored_hash");
                    }
                }
                result = authResult.toString();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            result = "{\"status\": \"error\":, \"message\": \"Database error\"}";
        } finally {
            if(conn != null){
                try{
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return result;
    }
}
