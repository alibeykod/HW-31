package ir.maktabsharif.service.business.validationService;

import ir.maktabsharif.exception.Business;
import ir.maktabsharif.model.dto.request.TaskRequestDTO;
import ir.maktabsharif.model.dto.response.ProjectResponseDTO;
import ir.maktabsharif.model.dto.response.TaskResponseDTO;
import ir.maktabsharif.repository.impl.TaskRepositoryImpl;
import ir.maktabsharif.service.business.databaseService.TaskService;

import java.util.List;
import java.util.Optional;

public class ValidatorTask {
    private final TaskService taskService = new TaskService();
    private final TaskRepositoryImpl taskRepository;
    public ValidatorTask(TaskRepositoryImpl taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void validations(TaskRequestDTO taskRequestDTO) throws Business{
        if (taskRequestDTO == null){
            throw new Business("No Task Found !!!");
        }
        if (taskRequestDTO.getTitle() == null || taskRequestDTO.getTitle().isBlank()){
            throw new Business("Title Can Not Be Null Or Empty !!!");
        }
        if ( taskRequestDTO.getEstimatedMinutes() == null|| taskRequestDTO.getEstimatedMinutes() < 0){
            throw new Business("EstimatedMinutes Can Not negative Or Null !!!! ");
        }
        if (taskRequestDTO.getAssigneeId() == null) {
            throw new Business("AssigneeId Can not Be Empty Or Null !!!");
        }
        if (taskRequestDTO.getProjectId() == null){
            throw new Business("Project ID Can Not Be Null !!!");
        }
    }

    public void add(TaskRequestDTO taskRequestDTO) throws Business{
        validations(taskRequestDTO);
        taskService.add(taskRequestDTO);
    }

    public TaskResponseDTO update(TaskRequestDTO taskRequestDTO , Long id)throws Business{
        validations(taskRequestDTO);
        return taskService.update(taskRequestDTO , id);
    }

    public Optional<TaskResponseDTO> findById(Long id)throws Business{
        if (id == null || id < 0 ){
            throw new Business("Id Can Not Be Null Or Empty Or Negative !!!");
        }
        return Optional.ofNullable(taskService.findById(id));
    }

    public List<TaskResponseDTO> findByAssigneeId(Long id)throws Business{
        if (id == null){
            throw new Business("Id Can Not Be Null !!!");
        }
        List<TaskResponseDTO> tasks = taskService.findByAssigneeId(id);
        if (tasks == null || tasks.isEmpty()){
            throw new Business("No Task Was Found !!!");
        }
        return tasks;
    }

    public List<TaskResponseDTO> findByProjectId(Long id)throws Business{
        if (id == null){
            throw new Business("Id Can Not Be Null !!!");
        }
        List<TaskResponseDTO> tasks = taskService.findByProjectId(id);
        if (tasks == null || tasks.isEmpty()){
            throw new Business("No Task Found !!!!");
        }
        return tasks;
    }

    public boolean delete(Long id)throws Business{
        if (id == null){
            throw new Business("Id Can Not Be Null !!!");
        }
        taskService.delete(id);
        return true;
    }

    public List<TaskResponseDTO> findAll()throws Business{
        List<TaskResponseDTO> tasks = taskService.findAll();
        if (tasks == null || tasks.isEmpty()){
            throw new Business("No Task Was Found !!!");
        }
        return tasks;
    }

}
