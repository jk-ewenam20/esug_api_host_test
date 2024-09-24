package com.sesElearning.sesElearningPlatform.controllers;



import com.sesElearning.sesElearningPlatform.connections.db_settings;
import com.sesElearning.sesElearningPlatform.models.PastQuestions;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/past_questions")
public class pastquestions_service {

    private PastQuestions pastQuestionsModel = new PastQuestions();

    @Autowired
    private db_settings cls_db_config;

    @CrossOrigin(origins = "*")
    @PostMapping("/add")
    public String addPastQuestion(@RequestBody String questionData) {
        pastQuestionsModel.con = cls_db_config.getCon();
        String result = pastQuestionsModel.addPastQuestion(questionData);
        return result;
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete_past_question")
    public String delete_course(@RequestParam String _title){
        pastQuestionsModel.con = cls_db_config.getCon();
        String result = pastQuestionsModel.delete_past_question(_title);
        return result;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/list")
    public String listPastQuestions() {
        pastQuestionsModel.con = cls_db_config.getCon();
        String result = pastQuestionsModel.getAllPastQuestions();
        return result;
    }
}


