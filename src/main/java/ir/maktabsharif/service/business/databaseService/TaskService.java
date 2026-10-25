package ir.maktabsharif.service.business.databaseService;

import ir.maktabsharif.convertor.Convertor;
import ir.maktabsharif.model.dao.TaskDAO;
import ir.maktabsharif.model.dto.request.TaskRequestDTO;
import ir.maktabsharif.model.dto.response.TaskResponseDTO;
import ir.maktabsharif.repository.impl.TaskRepositoryImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskService {

    protected final TaskRepositoryImpl repository = new TaskRepositoryImpl(TaskDAO.class);

    public void add(TaskRequestDTO taskRequestDTO) {
        repository.add(Convertor.TaskRequestDTOToTaskDao(taskRequestDTO));
    }

    public TaskResponseDTO update(TaskRequestDTO taskRequestDTO, Long id) {
        Optional<TaskDAO> found = repository.findById(id);
        if (!found.isPresent()) {
            return null;
        }
        TaskDAO existingTask = found.get();

        existingTask.setTitle(taskRequestDTO.getTitle());
        existingTask.setEstimatedMinutes(taskRequestDTO.getEstimatedMinutes());

        return Convertor.
                TaskDaoToTaskResponseDTO(repository.update(existingTask));
    }

    public List<TaskResponseDTO> findByAssigneeId(Long id) {
        List<TaskDAO> tasksByAssigneeId = repository.findByAssignee(id);
        return tasksByAssigneeId.stream()
                .map(Convertor::TaskDaoToTaskResponseDTO).
                collect(Collectors.toList());
    }

    public List<TaskResponseDTO> findByProjectId(Long id){
        List<TaskDAO> tasksWithProjectId = repository.findByProjectId(id);
        return tasksWithProjectId
                .stream()
                .map(Convertor::TaskDaoToTaskResponseDTO)
                .collect(Collectors.toList());
    }

    public TaskResponseDTO findById(Long id) {
        return repository.findById(id)
                .map(Convertor::TaskDaoToTaskResponseDTO)
                .orElse(null);
    }

    public List<TaskResponseDTO> findAll(){
        return repository.findAll()
                .stream().
                map(Convertor::TaskDaoToTaskResponseDTO).
                collect(Collectors.toList());
    }

    public boolean delete(Long id){
        Optional<TaskDAO> found = repository.findById(id);
        if (!found.isPresent()){
            System.out.println("Task With Id : [" +id+"] Didn't Find");
            return false;
        }

        try {
            repository.delete(id);
            return true;
        }catch (RuntimeException e){
            System.out.println("Task Did Not Delete !!!" + e.getMessage());
            return false;
        }
    }

}
