package ir.maktabsharif.model.dto.response;

import ir.maktabsharif.model.dao.Role;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class UserResponseDTO {

    private Role role;

    private Long id;

    private String name;

    private String email;
}
