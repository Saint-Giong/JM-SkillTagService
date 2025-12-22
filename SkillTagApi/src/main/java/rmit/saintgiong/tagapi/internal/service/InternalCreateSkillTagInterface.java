package rmit.saintgiong.tagapi.internal.service;

import rmit.saintgiong.tagapi.internal.dto.SkillTagRequestDto;
import rmit.saintgiong.tagapi.internal.dto.SkillTagResponseDto;

public interface InternalCreateSkillTagInterface {

    SkillTagResponseDto createSkillTag(SkillTagRequestDto request);
}
