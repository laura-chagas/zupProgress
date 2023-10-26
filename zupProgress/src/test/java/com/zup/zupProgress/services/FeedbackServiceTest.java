package com.zup.zupProgress.services;

import com.zup.zupProgress.dto.FeedbackDTO;
import com.zup.zupProgress.model.ChallengeModel;
import com.zup.zupProgress.model.FeedbackModel;
import com.zup.zupProgress.model.TypeOfAssessment;
import com.zup.zupProgress.repositories.ChallengeRepository;
import com.zup.zupProgress.repositories.FeedbackRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class FeedbackServiceTest{

    @InjectMocks
    private FeedbackService feedbackService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private FeedbackRepository feedbackRepository;

    @Mock
    private ChallengeRepository challengeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


}