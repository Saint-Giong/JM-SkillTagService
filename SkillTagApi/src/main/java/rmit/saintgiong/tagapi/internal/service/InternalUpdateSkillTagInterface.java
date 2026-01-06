package rmit.saintgiong.tagapi.internal.service;

import rmit.saintgiong.tagapi.internal.common.dto.SkillTagResponseDto;
import rmit.saintgiong.tagapi.internal.common.dto.SkillTagRequestDto;

public interface InternalUpdateSkillTagInterface {

    SkillTagResponseDto updateSkillTag(Long id, SkillTagRequestDto request);
}
