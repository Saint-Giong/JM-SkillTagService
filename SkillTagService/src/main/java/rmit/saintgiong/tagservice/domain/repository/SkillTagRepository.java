package rmit.saintgiong.tagservice.domain.repository;

import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rmit.saintgiong.tagservice.domain.entity.SkillTag;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillTagRepository extends JpaRepository<SkillTag, Long> {

    @NullMarked
    Page<SkillTag> findAll(Pageable pageable);

    List<SkillTag> findByNameContainingIgnoreCase(String keyword);
    Optional<SkillTag> findByName(String name);
}
