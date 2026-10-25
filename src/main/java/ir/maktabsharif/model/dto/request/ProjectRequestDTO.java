package ir.maktabsharif.model.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequestDTO {

    private Long managerId;

    private String name;

    private String description;
}
