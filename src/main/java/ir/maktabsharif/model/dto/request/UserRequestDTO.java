package ir.maktabsharif.model.dto.request;

import ir.maktabsharif.model.dao.Role;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class UserRequestDTO {

    private Role role;

    private String name;

    private String email;
}
