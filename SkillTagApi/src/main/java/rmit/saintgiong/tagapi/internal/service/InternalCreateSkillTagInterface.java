package rmit.saintgiong.tagapi.internal.service;

import rmit.saintgiong.tagapi.internal.common.dto.SkillTagRequestDto;
import rmit.saintgiong.tagapi.internal.common.dto.SkillTagResponseDto;

public interface InternalCreateSkillTagInterface {

    SkillTagResponseDto createSkillTag(SkillTagRequestDto request);
}
