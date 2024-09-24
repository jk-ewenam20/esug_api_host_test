package com.sesElearning.sesElearningPlatform.controllers;


import com.sesElearning.sesElearningPlatform.connections.db_settings;
import com.sesElearning.sesElearningPlatform.models.students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class students_services {
    students students = new students();

    @Autowired
    private db_settings cls_db_config;

    @CrossOrigin(origins = "*")
    @GetMapping("/all_students")
    public String all_students(){
        students.con = cls_db_config.getCon();
        String result = students.select_all_students();
        return result;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/specific_students")
    public String specific_student(@RequestParam Long student_id){
        students.con = cls_db_config.getCon();
        String result = students.select_student_by_id(student_id);
        return result;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/auth_student")
    public Object auth_student(@RequestParam Long student_id, @RequestParam String password){
        students.con = cls_db_config.getCon();
        Object result = students.authenticate_student(student_id, password);

        return result;
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/update_password")
    public String update_password(@RequestBody String studentFormData){
        students.con = cls_db_config.getCon();
        Object result = students.edit_student_password(studentFormData);

        return (String) result;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/add_students")
    public String create_student_user(@RequestBody String p_student_data){
        students.con = cls_db_config.getCon();
        String result = students.add_student(p_student_data);
        return result;

    }
}
