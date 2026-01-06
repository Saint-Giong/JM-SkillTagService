package rmit.saintgiong.tagservice.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "skill_tags")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
