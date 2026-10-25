package ir.maktabsharif.model.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "projects")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProjectDAO extends BaseEntity{

    @Column(nullable = false)
    private Long managerId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

}

