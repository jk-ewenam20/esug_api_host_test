package com.sesElearning.sesElearningPlatform.controllers;

import com.sesElearning.sesElearningPlatform.connections.db_settings;
import com.sesElearning.sesElearningPlatform.models.Submissions;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/submissions")
public class submissions_service {

    private Submissions submissionsModel = new Submissions();

    @Autowired
    private db_settings cls_db_config;


    @CrossOrigin(origins = "*")
    @PostMapping("/add")
    public String addSubmission(@RequestBody String submissionData) {
        submissionsModel.con = cls_db_config.getCon();
        return submissionsModel.addSubmission(submissionData);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/list")
    public String listSubmissions() {
        submissionsModel.con = cls_db_config.getCon();
        return submissionsModel.getAllSubmissions();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/grade")
    public String gradeSubmission(@RequestBody String gradingData) {
        submissionsModel.con = cls_db_config.getCon();
        return submissionsModel.gradeSubmission(gradingData);
    }
}