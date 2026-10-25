package ir.maktabsharif.service.business.databaseService;

import ir.maktabsharif.convertor.Convertor;
import ir.maktabsharif.model.dao.ProjectDAO;
import ir.maktabsharif.model.dto.request.ProjectRequestDTO;
import ir.maktabsharif.model.dto.response.ProjectResponseDTO;
import ir.maktabsharif.repository.impl.ProjectRepositoryImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProjectService {
    private final ProjectRepositoryImpl repository = new ProjectRepositoryImpl(ProjectDAO.class);

    public void add(ProjectRequestDTO projectRequestDTO){
        repository.add(Convertor.ProjectRequestDtoToProjectDao(projectRequestDTO));
    }

    public ProjectResponseDTO findById(Long id){
        Optional<ProjectDAO> toFound = repository.findById(id);
        if (!toFound.isPresent()){
            System.out.println("Project With Id => [" + id + "] Did Not Found !!!");
            return null;
        }
        ProjectDAO projectFound = toFound.get();
        return Convertor.ProjectDaoToProjectResponseDto(projectFound);
    }

    public List<ProjectResponseDTO> findAll(){
        return repository.findAll()
                .stream()
                .map(Convertor::ProjectDaoToProjectResponseDto)
                .collect(Collectors.toList());
    }

    public List<ProjectResponseDTO> findByManagerId(Long managerId){
        return repository.findByManagerId(managerId).stream()
                .map(Convertor::ProjectDaoToProjectResponseDto)
                .collect(Collectors.toList());
    }
}
