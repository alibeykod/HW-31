package ir.maktabsharif.model.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class ProjectResponseDTO {
    private Long managerId;

    private String name;

    private String description;

}