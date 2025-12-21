package rmit.saintgiong.tagapi.internal.service;

import rmit.saintgiong.tagapi.internal.dto.SkillTagDto;
import rmit.saintgiong.tagapi.internal.dto.CreateSkillTagRequestDto;

public interface InternalUpdateSkillTagInterface {

    SkillTagDto updateSkillTag(Long id, CreateSkillTagRequestDto request);
}
