package rmit.saintgiong.tagapi.internal.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rmit.saintgiong.tagapi.internal.common.dto.SkillTagResponseDto;

import java.util.List;

public interface InternalGetSkillTagInterface {

    SkillTagResponseDto getSkillTagById(Long id);
    Page<SkillTagResponseDto> getAllSkillTags(Pageable pageable);
    List<SkillTagResponseDto> autocompleteSkillTags(String prefix);
}
