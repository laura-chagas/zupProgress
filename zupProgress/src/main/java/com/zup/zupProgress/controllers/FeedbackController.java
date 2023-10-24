package com.zup.zupProgress.controllers;

import com.zup.zupProgress.dto.FeedbackDTO;
import com.zup.zupProgress.model.TypeOfAssessment;
import com.zup.zupProgress.services.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/feedback")
@CrossOrigin("*")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @PostMapping("/{challengeName}/{studentName}")
    public ResponseEntity<List<FeedbackDTO>> createFeedbackList(@RequestBody List<FeedbackDTO> feedbackList, @PathVariable(name = "challengeName") String challengeName, @PathVariable(name = "studentName") String studentName) {
        List<FeedbackDTO> savedFeedbackList = new ArrayList<>(); // Crie uma lista para armazenar os feedbacks salvos
        feedbackList.forEach(feedbackDTO -> {
            FeedbackDTO savedFeedback = feedbackService.createFeedback(feedbackDTO, challengeName, studentName);
            savedFeedbackList.add(savedFeedback); // Adicione cada feedback salvo à lista
        });
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFeedbackList);
    }

    @GetMapping("/{challengeName}/{type}")
    public ResponseEntity<List<FeedbackDTO>> getFeedbacks( @PathVariable(name = "challengeName") String challengeName, @PathVariable(name = "type") String type){
        TypeOfAssessment typeOfAssessment = TypeOfAssessment.valueOf(type);
        List<FeedbackDTO> feedbackByChallengeNameAndType = feedbackService.findFeedbackByChallengeNameAndType(challengeName, typeOfAssessment);

        return ResponseEntity.ok(feedbackByChallengeNameAndType);

    }
}
