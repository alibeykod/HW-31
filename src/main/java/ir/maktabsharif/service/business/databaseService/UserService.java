package ir.maktabsharif.service.business.databaseService;

import ir.maktabsharif.convertor.Convertor;
import ir.maktabsharif.model.dao.UserDAO;
import ir.maktabsharif.model.dto.request.UserRequestDTO;
import ir.maktabsharif.model.dto.response.UserResponseDTO;
import ir.maktabsharif.repository.impl.UserRepositoryImpl;
import ir.maktabsharif.util.HibernateUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {
    private final UserRepositoryImpl repository = new UserRepositoryImpl(UserDAO.class);

    public UserResponseDTO add(UserRequestDTO userRequestDTO) {
        UserDAO userDAO = repository.add(Convertor.userReqDtoToUserDao(userRequestDTO));
        return Convertor.userDaoToUserResponseDTO(userDAO);
    }

    public UserResponseDTO findById(Long id) {
        Optional<UserDAO> foundUser = repository.findById(id);
        if (!foundUser.isPresent()) {
            System.out.println("User With Id => [" + id + "] Didn't Found In Database !!!");
            return null;
        }
        UserDAO toClient = foundUser.get();
        return Convertor.userDaoToUserResponseDTO(toClient);
    }

    public List<UserResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(Convertor::userDaoToUserResponseDTO)
                .collect(Collectors.toList());
    }

    public String findAssigneeNameWithTaskId(Long id) {
        return repository.findAssigneeNameWithTaskId(id);
    }
}

