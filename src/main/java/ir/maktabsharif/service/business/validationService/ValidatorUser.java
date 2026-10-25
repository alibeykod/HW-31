package ir.maktabsharif.service.business.validationService;

import ir.maktabsharif.exception.Business;
import ir.maktabsharif.model.dto.request.UserRequestDTO;
import ir.maktabsharif.model.dto.response.UserResponseDTO;
import ir.maktabsharif.service.business.databaseService.UserService;

import java.util.List;
import java.util.Optional;

public class ValidatorUser {
    private final UserService userService = new UserService();

    public void validations(UserRequestDTO userRequestDTO) throws Business {
        if (userRequestDTO == null) {
            throw new Business("No User Request Dto Found !!!");
        }
        if (userRequestDTO.getEmail() == null || userRequestDTO.getEmail().isBlank()) {
            throw new Business("User Email Can Not Be Null Or Empty !!!");
        }
        if (userRequestDTO.getName() == null || userRequestDTO.getName().isBlank()) {
            throw new Business("Name Of User Was Not Found !!!");
        }
        if (userRequestDTO.getRole() == null) {
            throw new Business("No Role Found or Was Empty !!!");
        }
    }

    public void add(UserRequestDTO userRequestDTO) throws Business {
        validations(userRequestDTO);
        userService.add(userRequestDTO);
    }

    public Optional<UserResponseDTO> findById(Long id) throws Business {
        if (id == null) {
            throw new Business("Id Was Null !!!!");
        }
        return Optional.ofNullable(userService.findById(id));
    }

    public List<UserResponseDTO> findAll() throws Business {
        List<UserResponseDTO> users = userService.findAll();
        if (users == null || users.isEmpty()) {
            throw new Business("No User Was Found !!!");
        }
        return users;
    }
}
