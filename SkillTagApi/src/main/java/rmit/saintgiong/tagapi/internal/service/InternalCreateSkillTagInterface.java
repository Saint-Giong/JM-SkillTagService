package rmit.saintgiong.tagapi.internal.service;

import rmit.saintgiong.tagapi.internal.dto.SkillTagDto;
import rmit.saintgiong.tagapi.internal.dto.CreateSkillTagRequestDto;

public interface InternalCreateSkillTagInterface {

    SkillTagDto createSkillTag(CreateSkillTagRequestDto request);
}
