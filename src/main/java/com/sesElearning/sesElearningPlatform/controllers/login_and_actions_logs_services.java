package com.sesElearning.sesElearningPlatform.controllers;


import com.sesElearning.sesElearningPlatform.connections.db_settings;
import com.sesElearning.sesElearningPlatform.models.login_and_actions_logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logs")
public class login_and_actions_logs_services {
    login_and_actions_logs logs = new login_and_actions_logs();

    @Autowired
    private db_settings cls_db_config;

    @CrossOrigin(origins = "*")
    @GetMapping("/all_login_logs")
    public String all_login_logs() {
        logs.con = cls_db_config.getCon();
        String results = logs.get_login_logs();
        return results;
    }


    @CrossOrigin(origins = "*")
    @GetMapping("/all_actions_logs")
    public String all_actions_logs() {
        logs.con = cls_db_config.getCon();
        String results = logs.get_actions_logs();
        return results;
    }


}
