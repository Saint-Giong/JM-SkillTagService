package rmit.saintgiong.tagservice.skilltag.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rmit.saintgiong.tagapi.external.dto.SkillTagDto;
import rmit.saintgiong.tagapi.internal.dto.CreateSkillTagRequestDto;
import rmit.saintgiong.tagapi.internal.service.InternalCreateSkillTagInterface;
import rmit.saintgiong.tagservice.skilltag.entity.SkillTag;
import rmit.saintgiong.tagservice.skilltag.mapper.SkillTagMapper;
import rmit.saintgiong.tagservice.skilltag.repository.SkillTagRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateSkillTagService implements InternalCreateSkillTagInterface {

    private final SkillTagRepository skillTagRepository;
    private final SkillTagMapper skillTagMapper;

    @Override
    public SkillTagDto createSkillTag(CreateSkillTagRequestDto request) {
        String upperCaseName = request.getName().toUpperCase();
        
        if (skillTagRepository.existsByNameIgnoreCase(upperCaseName)) {
            throw new IllegalArgumentException("Skill tag with name '" + request.getName() + "' already exists");
        }

        SkillTag skillTag = skillTagMapper.toEntity(request);
        skillTag.setName(upperCaseName);
        SkillTag savedSkillTag = skillTagRepository.save(skillTag);
        return skillTagMapper.toDto(savedSkillTag);
    }
}
