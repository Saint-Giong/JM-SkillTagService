package rmit.saintgiong.tagservice.skilltag.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rmit.saintgiong.tagapi.internal.dto.SkillTagDto;
import rmit.saintgiong.tagapi.internal.service.InternalGetSkillTagInterface;
import rmit.saintgiong.tagservice.skilltag.entity.SkillTag;
import rmit.saintgiong.tagservice.skilltag.mapper.SkillTagMapper;
import rmit.saintgiong.tagservice.skilltag.repository.SkillTagRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetSkillTagService implements InternalGetSkillTagInterface {

    private final SkillTagRepository skillTagRepository;
    private final SkillTagMapper skillTagMapper;

    @Override
    public SkillTagDto getSkillTagById(Long id) {
        SkillTag skillTag = skillTagRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Skill tag not found with id: " + id));
        return skillTagMapper.toDto(skillTag);
    }

    @Override
    public List<SkillTagDto> getAllSkillTags() {
        List<SkillTag> skillTags = skillTagRepository.findAll();
        return skillTagMapper.toDtoList(skillTags);
    }
}
