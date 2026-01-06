package rmit.saintgiong.tagservice.domain.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rmit.saintgiong.tagapi.internal.service.InternalDeleteSkillTagInterface;

@Service
@RequiredArgsConstructor
@Transactional
public class SkillTagDeleteService implements InternalDeleteSkillTagInterface {

    private final SkillTagRepository skillTagRepository;

    @Override
    public void deleteSkillTag(Long id) {
        if (!skillTagRepository.existsById(id)) {
            throw new IllegalArgumentException("Skill tag not found with id: " + id);
        }
        skillTagRepository.deleteById(id);
    }
}
