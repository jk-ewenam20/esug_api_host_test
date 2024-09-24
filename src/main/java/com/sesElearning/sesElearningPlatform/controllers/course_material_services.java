package com.sesElearning.sesElearningPlatform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sesElearning.sesElearningPlatform.connections.db_settings;
import com.sesElearning.sesElearningPlatform.models.course_material;

@RestController
@RequestMapping("/api/all_courses_materials")
public class course_material_services {
  course_material course_material_l = new course_material();

  @Autowired
  private db_settings cls_db_config;

  @CrossOrigin(origins = "*")
  @GetMapping("/get_course_materials")
  public String get_course_material() {
    course_material_l.con = cls_db_config.getCon();
    String result =  course_material_l.get_course_materials();
    return result;
  }
  @CrossOrigin(origins = "*")
  @DeleteMapping("/delete_material")
  public String delete_course(@RequestParam String _title){
    course_material_l.con = cls_db_config.getCon();
    String result = course_material_l.delete_material(_title);
    return result;
  }

  @CrossOrigin(origins = "*")
    @PostMapping("/add_course_material")
    public String add_course(@RequestBody String courseMaterialData) {
        course_material_l.con = cls_db_config.getCon();
        String result = course_material_l.add_course_material(courseMaterialData);
        return result;
    }
}
