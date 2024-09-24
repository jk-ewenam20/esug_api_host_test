package com.sesElearning.sesElearningPlatform.controllers;


import com.sesElearning.sesElearningPlatform.connections.db_settings;
import com.sesElearning.sesElearningPlatform.models.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class admin_services {

    admin administrator = new admin();

    @Autowired
    private db_settings cls_db_config;


    @CrossOrigin(origins = "*")
    @GetMapping("/all_administrators")
    public String all_administrators(){
        administrator.con = cls_db_config.getCon();
        String result = administrator.select_all_administrators();
        return result;
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/update_password")
    public String update_password(@RequestBody String adminFormData){
        administrator.con = cls_db_config.getCon();
        Object result = administrator.reset_admin_password(adminFormData);

        return (String) result;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/get_admin")
    public String admin_user(@RequestParam Long admin_id) {
        administrator.con = cls_db_config.getCon();
        String result = administrator.select_admin(admin_id);
        return result;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/create_admin")
    public String create_admin(@RequestBody String admin_data) {
        administrator.con = cls_db_config.getCon();
        String result =  administrator.add_admin_user(admin_data);
        return result;
    }

}
