package rmit.saintgiong.tagservice.common.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rmit.saintgiong.tagservice.domain.entity.SkillTag;
import rmit.saintgiong.tagservice.domain.repository.SkillTagRepository;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeedingConfig implements CommandLineRunner {

    private final SkillTagRepository skillTagRepository;

    private static final List<String> DEFAULT_SKILL_TAGS = Arrays.asList(
            "JAVA",
            "PYTHON",
            "JAVASCRIPT",
            "TYPESCRIPT",
            "REACT",
            "ANGULAR",
            "VUE",
            "SPRING BOOT",
            "NODE.JS",
            "SQL",
            "MONGODB",
            "DOCKER",
            "KUBERNETES",
            "AWS",
            "AZURE",
            "GIT",
            "REST API",
            "GRAPHQL",
            "HTML",
            "CSS"
    );

    @Override
    public void run(String @NonNull ... args) {
        if (skillTagRepository.count() == 0) {
            log.info("No skill tags found in database. Populating default skill tags...");
            
            List<SkillTag> defaultTags = DEFAULT_SKILL_TAGS.stream()
                    .map(name -> SkillTag.builder().name(name).build())
                    .toList();
            
            skillTagRepository.saveAll(defaultTags);
            log.info("Successfully populated {} default skill tags.", defaultTags.size());
        } else {
            log.info("Skill tags already exist in database. Skipping initialization.");
        }
    }
}
