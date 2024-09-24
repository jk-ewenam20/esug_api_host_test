package com.sesElearning.sesElearningPlatform.controllers;


import com.sesElearning.sesElearningPlatform.connections.db_settings;
import com.sesElearning.sesElearningPlatform.models.Assignments;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assignments")
public class assignments_service {

    private Assignments assignmentsModel = new Assignments();

    @Autowired
    private db_settings cls_db_config;

    @CrossOrigin(origins = "*")
    @PostMapping("/add")
    public String addAssignment(@RequestBody String assignmentData) {
        assignmentsModel.con = cls_db_config.getCon();
        String result = assignmentsModel.addAssignment(assignmentData);
        return result;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/list")
    public String listAssignments() {
        assignmentsModel.con = cls_db_config.getCon();
        String result = assignmentsModel.getAllAssignments();
        return result;
    }
}