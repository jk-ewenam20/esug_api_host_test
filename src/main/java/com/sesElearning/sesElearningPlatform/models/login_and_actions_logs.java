package com.sesElearning.sesElearningPlatform.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class login_and_actions_logs {
    public Connection con = null;

    public String get_login_logs () {
        String result  = null;
        String SQL = "SELECT public.get_all_login_logs()";
        Connection conn = con;
        try {

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            ResultSet response = pstmt.executeQuery();
            while (response.next()) {
                result = response.getString("get_all_login_logs");
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

    public String get_actions_logs () {
        String result  = null;
        String SQL = "SELECT public.get_all_action_logs()";
        Connection conn = con;

        try {

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            ResultSet response = pstmt.executeQuery();
            while (response.next()) {
                result = response.getString("get_all_action_logs");
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
