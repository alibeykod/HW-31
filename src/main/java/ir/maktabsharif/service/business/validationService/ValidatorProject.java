package ir.maktabsharif.service.business.validationService;

import ir.maktabsharif.exception.Business;
import ir.maktabsharif.model.dto.request.ProjectRequestDTO;
import ir.maktabsharif.model.dto.response.ProjectResponseDTO;
import ir.maktabsharif.repository.impl.ProjectRepositoryImpl;
import ir.maktabsharif.service.business.databaseService.ProjectService;

import java.util.List;
import java.util.Optional;

public class ValidatorProject {

    private final ProjectService projectService = new ProjectService();

    private final ProjectRepositoryImpl projectRepository;

    public ValidatorProject(ProjectRepositoryImpl projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void validations(ProjectRequestDTO projectRequestDTO) throws Business {
        if (projectRequestDTO == null) {
            throw new Business("No Project Found In Request !!!");
        }
        if (projectRequestDTO.getManagerId() == null || projectRequestDTO.getManagerId() < 0) {
            throw new Business("Manager ID Was Null !!!");
        }
        if (projectRequestDTO.getDescription() == null || projectRequestDTO.getDescription().isBlank()) {
            throw new Business("No Description Found !!!");
        }
        if (projectRequestDTO.getName() == null || projectRequestDTO.getName().isBlank()) {
            throw new Business("Name Of Project Was Empty or Null !!!");
        }
    }

    public ProjectResponseDTO add(ProjectRequestDTO projectRequestDTO) throws Business {
        validations(projectRequestDTO);
        return projectService.add(projectRequestDTO);
    }

    public Optional<ProjectResponseDTO> findById(Long id) throws Business {
        if (id == null || id < 0) {
            throw new Business("No Id Found In Request !!!");
        }
        return Optional.ofNullable(projectService.findById(id));
    }

    public List<ProjectResponseDTO> findByManagerId(Long id) throws Business {
        if (id == null || id < 0) {
            throw new Business("No Id Found In Request !!!");
        }
        List<ProjectResponseDTO> projects = projectService.findByManagerId(id);
        if (projects == null || projects.isEmpty()) {
            throw new Business("No Project Found !!!");
        }
        return projects;
    }

    public List<ProjectResponseDTO> findAll() throws Business {
        List<ProjectResponseDTO> projects = projectService.findAll();
        if (projects == null || projects.isEmpty()) {
            throw new Business("No Project Found !!!");
        }
        return projects;
    }
}
