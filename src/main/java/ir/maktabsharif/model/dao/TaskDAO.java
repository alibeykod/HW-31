package ir.maktabsharif.model.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "tasks")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TaskDAO extends BaseEntity{


    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer estimatedMinutes;

    @Column(nullable = false)
    private Long assigneeId;

    @Column(nullable = false)
    private Long projectId;

}
