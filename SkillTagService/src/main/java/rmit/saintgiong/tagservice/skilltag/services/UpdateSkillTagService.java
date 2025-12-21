package rmit.saintgiong.tagservice.skilltag.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rmit.saintgiong.tagapi.internal.dto.SkillTagRequestDto;
import rmit.saintgiong.tagapi.internal.dto.SkillTagResponseDto;
import rmit.saintgiong.tagapi.internal.service.InternalUpdateSkillTagInterface;
import rmit.saintgiong.tagservice.common.exception.SkillTagAlreadyExistsException;
import rmit.saintgiong.tagservice.skilltag.entity.SkillTag;
import rmit.saintgiong.tagservice.skilltag.mapper.SkillTagMapper;
import rmit.saintgiong.tagservice.skilltag.repository.SkillTagRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateSkillTagService implements InternalUpdateSkillTagInterface {

    private final SkillTagRepository skillTagRepository;
    private final SkillTagMapper skillTagMapper;

    @Override
    public SkillTagResponseDto updateSkillTag(Long id, SkillTagRequestDto request) {
        SkillTag existingSkillTag = skillTagRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Skill tag not found with id: " + id));

        String upperCaseName = request.getName().toUpperCase();
        // Check if new name already exists for another skill tag (case-insensitive)
        skillTagRepository.findByNameIgnoreCase(upperCaseName)
                .ifPresent(existingTag -> {
                    SkillTagResponseDto existingTagDto = skillTagMapper.toDto(existingTag);
                    throw new SkillTagAlreadyExistsException(
                            String.format("Skill tag with name '%s' already exists. Existing tag: [id=%d, name=%s]",
                                    request.getName(), existingTag.getId(), existingTag.getName()),
                            existingTagDto
                    );
                });
        existingSkillTag.setName(upperCaseName);
        SkillTag updatedSkillTag = skillTagRepository.save(existingSkillTag);
        return skillTagMapper.toDto(updatedSkillTag);
    }
}
