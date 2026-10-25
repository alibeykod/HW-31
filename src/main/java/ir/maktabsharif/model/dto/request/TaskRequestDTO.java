package ir.maktabsharif.model.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class TaskRequestDTO {
    private String title;

    private Integer estimatedMinutes;

    private Long assigneeId;

    private Long projectId;
}
