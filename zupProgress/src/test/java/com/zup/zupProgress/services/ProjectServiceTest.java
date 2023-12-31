package com.zup.zupProgress.services;

import com.zup.zupProgress.dto.ProjectDTO;
import com.zup.zupProgress.dto.StudentDTO;
import com.zup.zupProgress.model.ProjectModel;
import com.zup.zupProgress.model.StudentModel;
import com.zup.zupProgress.repositories.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProjectService projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProject() {
        ProjectDTO inputDTO = new ProjectDTO();
        inputDTO.setName("Test Project");
        ProjectModel projectModel = new ProjectModel();
        projectModel.setName("Test Project");

        when(modelMapper.map(inputDTO, ProjectModel.class)).thenReturn(projectModel);
        when(projectRepository.save(projectModel)).thenReturn(projectModel);
        when(modelMapper.map(projectModel, ProjectDTO.class)).thenReturn(inputDTO);

        ProjectDTO resultDTO = projectService.createProject(inputDTO);

        assertEquals(inputDTO.getName(), resultDTO.getName());
    }

    @Test
    void testGetByName() {
        String projectName = "Test Project";
        ProjectModel projectModel = new ProjectModel();
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setName(projectName);
        projectModel.setName(projectName);

        when(modelMapper.map(eq(projectModel), eq(ProjectDTO.class))).thenReturn(projectDTO);
        when(projectRepository.findByName(projectName)).thenReturn(Optional.of(projectModel));
        ProjectDTO resultDTO = projectService.getByName(projectName);
        assertNotNull(resultDTO);
        assertEquals(projectName, resultDTO.getName());
    }

    @Test
    void testGetByNameNotFound() {
        String projectName = "Nonexistent Project";

        when(projectRepository.findByName(projectName)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            projectService.getByName(projectName);
        });
    }

    @Test
    void testGetAllProjectsName(){
        when(projectRepository.getAllProjectName()).thenReturn(Collections.singletonList("Estrelas"));
        List<String> allProjectName = projectService.getAllProjectName();
        assertEquals("Estrelas",allProjectName.get(0));
    }

    @Test
    void studentsNamesByProjectName(){
        when(projectRepository.findStudentNamesByProjectName("Estrelas")).thenReturn(Collections.singletonList("Ana"));
        List<String> studentsNamesByProjectName = projectService.studentsNamesByProjectName("Estrelas");

        assertEquals("Ana",studentsNamesByProjectName.get(0));
    }
    @Test
    void studentsByProjectName(){
        StudentModel studentModel = new StudentModel();

        when(projectRepository.findStudentByProjectName("Estrelas")).thenReturn(Collections.singletonList(studentModel));
        List<StudentDTO> studentsByProjectName = projectService.studentsByProjectName("Estrelas");

        assertNotNull(studentsByProjectName);
    }

}
