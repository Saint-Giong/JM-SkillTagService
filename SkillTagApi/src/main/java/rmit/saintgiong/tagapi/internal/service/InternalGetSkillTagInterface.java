package rmit.saintgiong.tagapi.internal.service;

import rmit.saintgiong.tagapi.internal.dto.SkillTagResponseDto;

import java.util.List;

public interface InternalGetSkillTagInterface {

    SkillTagResponseDto getSkillTagById(Long id);

    List<SkillTagResponseDto> getAllSkillTags();
}
