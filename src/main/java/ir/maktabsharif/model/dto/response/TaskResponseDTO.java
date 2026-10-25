package ir.maktabsharif.model.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class TaskResponseDTO {
    private Long id;

    private String title;

    private Integer estimatedMinutes;

    private Long assigneeId;

    private Long projectId;
}
