package rmit.saintgiong.tagservice.domain.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rmit.saintgiong.tagapi.internal.common.dto.SkillTagRequestDto;
import rmit.saintgiong.tagapi.internal.common.dto.SkillTagResponseDto;
import rmit.saintgiong.tagapi.internal.service.InternalUpdateSkillTagInterface;
import rmit.saintgiong.tagservice.common.exception.resources.SkillTagAlreadyExistsException;
import rmit.saintgiong.tagservice.domain.entity.SkillTag;
import rmit.saintgiong.tagservice.domain.mapper.SkillTagMapper;
import rmit.saintgiong.tagservice.domain.repository.SkillTagRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class SkillTagUpdateService implements InternalUpdateSkillTagInterface {

    private final SkillTagRepository skillTagRepository;
    private final SkillTagMapper skillTagMapper;

    @Override
    public SkillTagResponseDto updateSkillTag(Long id, SkillTagRequestDto request) {
        SkillTag existingSkillTag = skillTagRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Skill tag not found with id: " + id));

        String upperCaseName = request.getName().toUpperCase().trim();
        // Check if new name already exists for another skill tag (case-insensitive)
        skillTagRepository.findByName(upperCaseName)
                .ifPresent(existingTag -> {
                    if (!existingTag.getId().equals(id)){
                        SkillTagResponseDto existingTagDto = skillTagMapper.toDto(existingTag);
                        throw new SkillTagAlreadyExistsException(
                                String.format("Skill tag with name '%s' already exists. Existing tag: [id=%d, name=%s]",
                                        request.getName(), existingTag.getId(), existingTag.getName()),
                                existingTagDto
                        );
                    }
                });
        existingSkillTag.setName(upperCaseName);
        SkillTag updatedSkillTag = skillTagRepository.save(existingSkillTag);
        return skillTagMapper.toDto(updatedSkillTag);
    }
}
