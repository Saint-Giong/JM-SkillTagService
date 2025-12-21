package rmit.saintgiong.tagapi.internal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillTagDto {
    private Long id;
    private String name;
}
