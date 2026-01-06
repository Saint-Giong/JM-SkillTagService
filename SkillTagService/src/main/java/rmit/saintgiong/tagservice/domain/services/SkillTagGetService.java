package rmit.saintgiong.tagservice.domain.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rmit.saintgiong.tagapi.internal.dto.SkillTagResponseDto;
import rmit.saintgiong.tagapi.internal.service.InternalGetSkillTagInterface;
import rmit.saintgiong.tagservice.domain.entity.SkillTag;
import rmit.saintgiong.tagservice.domain.mapper.SkillTagMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SkillTagGetService implements InternalGetSkillTagInterface {

    private final SkillTagRepository skillTagRepository;
    private final SkillTagMapper skillTagMapper;

    @Override
    public SkillTagResponseDto getSkillTagById(Long id) {
        SkillTag skillTag = skillTagRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Skill tag not found with id: " + id));
        return skillTagMapper.toDto(skillTag);
    }
    @Override
    public Page<SkillTagResponseDto> getAllSkillTags(Pageable pageable) {
        return skillTagRepository.findAll(pageable)
                .map(skillTagMapper::toDto);
    }

    @Override
    public List<SkillTagResponseDto> autocompleteSkillTags(String keyword) {
        return skillTagRepository.findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(skillTagMapper::toDto)
                .collect(Collectors.toList());
    }
}
