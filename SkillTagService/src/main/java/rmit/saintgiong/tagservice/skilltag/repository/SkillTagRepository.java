package rmit.saintgiong.tagservice.skilltag.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rmit.saintgiong.tagservice.skilltag.entity.SkillTag;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillTagRepository extends JpaRepository<SkillTag, Long> {

    Optional<SkillTag> findByNameIgnoreCase(String name);

    Page<SkillTag> findAll(Pageable pageable);

    List<SkillTag> findByNameContainingIgnoreCase(String keyword);
}
