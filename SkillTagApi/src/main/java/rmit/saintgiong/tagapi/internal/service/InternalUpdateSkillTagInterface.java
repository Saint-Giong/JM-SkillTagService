package rmit.saintgiong.tagapi.internal.service;

import rmit.saintgiong.tagapi.internal.dto.SkillTagResponseDto;
import rmit.saintgiong.tagapi.internal.dto.SkillTagRequestDto;

public interface InternalUpdateSkillTagInterface {

    SkillTagResponseDto updateSkillTag(Long id, SkillTagRequestDto request);
}
