package com.sesElearning.sesElearningPlatform.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sesElearning.sesElearningPlatform.connections.db_settings;
import com.sesElearning.sesElearningPlatform.models.lecturers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lecturer")
public class lecturers_services {
    lecturers lecturer = new lecturers();

    @Autowired
    private db_settings cls_db_config;

    @CrossOrigin(origins = "*")
    @GetMapping("/get_all_lecturers")
    public String all_lecturers() {
        lecturer.con = cls_db_config.getCon();
        String result = lecturer.select_all_lecturers();
        return result;
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/update_password")
    public String update_password(@RequestBody String lecturerFormData){
        lecturer.con = cls_db_config.getCon();
        Object result = lecturer.reset_lecturer_password(lecturerFormData);

        return (String) result;
    }

    @CrossOrigin(origins="*")
    @PostMapping("/add_lecturer")
    public String add_lecturer(@RequestBody String lecturers) {
        lecturer.con = cls_db_config.getCon();
        String result = lecturer.add_lecturer_user(lecturers);
        return result;
    }

    @CrossOrigin(origins="*")
    @PostMapping("/assign_course_to_lecture")
    public String link_lecturer_and_course(@RequestParam String lecturer_id, @RequestParam String course_code) {
        lecturer.con = cls_db_config.getCon();
        String result = lecturer.link_lecturer_to_course(lecturer_id, course_code);
        return result;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/get_lecturer")
    public  String get_lecturer(@RequestParam Long lecturer_id) {
        lecturer.con = cls_db_config.getCon();
        String result = lecturer.select_lecturer_by_id(lecturer_id);
        return result;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/get_lecturer_and_course")
    public  String get_lecturer_and_course(@RequestParam String p_lecturer_id) {
        lecturer.con = cls_db_config.getCon();
        String result = lecturer.get_lecturer_and_assigned_courses(p_lecturer_id);
        return result;
    }
}
