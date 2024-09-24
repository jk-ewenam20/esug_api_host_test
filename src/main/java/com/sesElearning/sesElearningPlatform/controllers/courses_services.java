package com.sesElearning.sesElearningPlatform.controllers;


import com.sesElearning.sesElearningPlatform.connections.db_settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sesElearning.sesElearningPlatform.models.courses;

@RestController
@RequestMapping("/api/all_courses")
public class courses_services {

    courses course_l = new courses();

    @Autowired
    private db_settings cls_db_config;


    @CrossOrigin(origins = "*")
    @GetMapping("/list_of_courses")
    public String list_of_courses(){
        course_l.con = cls_db_config.getCon();
        String result = course_l.select_all_courses();
        return result;
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete_course")
    public String delete_course(@RequestParam String _course_code){
        course_l.con = cls_db_config.getCon();
        String result = course_l.delete_courses(_course_code);
        return result;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/add_course")
    public String add_course(@RequestBody String courseData) {
        course_l.con = cls_db_config.getCon();
        String result = course_l.addCourse(courseData);
        return result;
    }
}
