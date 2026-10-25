package ir.maktabsharif.convertor;

import ir.maktabsharif.model.dao.ProjectDAO;
import ir.maktabsharif.model.dao.TaskDAO;
import ir.maktabsharif.model.dao.UserDAO;
import ir.maktabsharif.model.dto.request.ProjectRequestDTO;
import ir.maktabsharif.model.dto.request.TaskRequestDTO;
import ir.maktabsharif.model.dto.request.UserRequestDTO;
import ir.maktabsharif.model.dto.response.ProjectResponseDTO;
import ir.maktabsharif.model.dto.response.TaskResponseDTO;
import ir.maktabsharif.model.dto.response.UserResponseDTO;

public class Convertor {
    public static UserDAO userReqDtoToUserDao(UserRequestDTO userRequestDTO){
        return new UserDAO(userRequestDTO.getRole() , userRequestDTO.getName(), userRequestDTO.getEmail());
    }
    public static UserResponseDTO userDaoToUserResponseDTO(UserDAO userDAO){
        return UserResponseDTO.builder()
                .id(userDAO.getId())
                .name(userDAO.getName())
                .email(userDAO.getEmail())
                .role(userDAO.getRole())
                .build();
    }

    public static TaskDAO TaskRequestDTOToTaskDao(TaskRequestDTO taskRequestDTO){
        return new TaskDAO(taskRequestDTO.getTitle(), taskRequestDTO.getEstimatedMinutes(), taskRequestDTO.getAssigneeId()
                , taskRequestDTO.getProjectId());
    }

    public static TaskResponseDTO TaskDaoToTaskResponseDTO(TaskDAO taskDAO){
        return TaskResponseDTO.builder()
                .id(taskDAO.getId())
                .title(taskDAO.getTitle())
                .assigneeId(taskDAO.getAssigneeId())
                .estimatedMinutes(taskDAO.getEstimatedMinutes())
                .projectId(taskDAO.getProjectId())
                .build();
    }

    public static ProjectDAO ProjectRequestDtoToProjectDao(ProjectRequestDTO projectRequestDTO){
        return new ProjectDAO(projectRequestDTO.getManagerId(), projectRequestDTO.getName(), projectRequestDTO.getDescription());
    }

    public static ProjectResponseDTO ProjectDaoToProjectResponseDto(ProjectDAO projectDAO){
        return ProjectResponseDTO.builder()
                .managerId(projectDAO.getManagerId())
                .name(projectDAO.getName())
                .description(projectDAO.getDescription())
                .build();
    }
}
