package com.sesElearning.sesElearningPlatform.models;

import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class lecturers {
    public Connection con = null;
    public String add_lecturer_user(String p_lecturer_data) {
        String result = null;
        String SQL = "SELECT * FROM user_private.add_lecturer(?::JSON)";
        Connection conn = con;
        try {
            // Parse the JSON request
            JSONObject jsonObject = new JSONObject(p_lecturer_data);

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
                result = rs.getString("add_lecturer");
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

    public String reset_lecturer_password(String lecturerFormData) {
        String result = null;
        String SQL = "SELECT * FROM user_private.update_lecturer_password(?::JSON)";
        Connection conn = con;
        try {
            // Parse the JSON request
            JSONObject jsonObject = new JSONObject(lecturerFormData);

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
                result = rs.getString("update_lecturer_password");
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

    public String link_lecturer_to_course(String lecturer_id,String course_code) {
        String result = null;
        String SQL = "SELECT * FROM user_private.link_lecturer_to_course(?,?)";
        Connection conn = con;
        try {

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, lecturer_id);
            pstmt.setString(2, course_code);
            ResultSet response = pstmt.executeQuery();
            while (response.next()) {
                result = response.getString("link_lecturer_to_course");
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

    public String get_lecturer_and_assigned_courses(String p_lecturer_id) {
        String result = null;
        String SQL = "SELECT * FROM user_private.get_lecturer_and_courses_by_id(?)";
        Connection conn = con;
        try {

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, p_lecturer_id);
            ResultSet response = pstmt.executeQuery();
            while (response.next()) {
                result = response.getString("get_lecturer_and_courses_by_id");
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

    public String select_all_lecturers() {
        String result = null;
        String SQL = "SELECT * FROM user_private.select_all_lecturers()";
        Connection conn = con;
        try {

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            ResultSet response = pstmt.executeQuery();
            while (response.next()) {
                result = response.getString("select_all_lecturers");
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

    public String select_lecturer_by_id(Long lecturer_id) {
        String result = null;
        String SQL = "SELECT * FROM user_private.get_lecturer_by_id(?)";
        Connection conn = con;
        try {

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setLong(1, lecturer_id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("get_lecturer_by_id");
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
