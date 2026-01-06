package rmit.saintgiong.tagapi.internal.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillTagResponseDto {
    private Long id;
    private String name;
}
