package rmit.saintgiong.tagapi.internal.service;

import rmit.saintgiong.tagapi.external.dto.SkillTagDto;

import java.util.List;

public interface InternalGetSkillTagInterface {

    SkillTagDto getSkillTagById(Long id);

    List<SkillTagDto> getAllSkillTags();
}
