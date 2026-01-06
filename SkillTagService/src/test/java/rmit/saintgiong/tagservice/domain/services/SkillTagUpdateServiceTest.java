package rmit.saintgiong.tagservice.domain.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rmit.saintgiong.tagapi.internal.dto.SkillTagRequestDto;
import rmit.saintgiong.tagapi.internal.dto.SkillTagResponseDto;
import rmit.saintgiong.tagservice.common.exception.SkillTagAlreadyExistsException;
import rmit.saintgiong.tagservice.domain.entity.SkillTag;
import rmit.saintgiong.tagservice.domain.mapper.SkillTagMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SkillTagUpdateServiceTest {
    @Mock
    private SkillTagRepository skillTagRepository;
    @Mock
    private SkillTagMapper skillTagMapper;
    @InjectMocks
    private SkillTagUpdateService skillTagUpdateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateSkillTag_success() {
        SkillTagRequestDto request = new SkillTagRequestDto("JAVA");
        SkillTag entity = SkillTag.builder().id(1L).name("JAVA").build();
        SkillTagResponseDto dto = new SkillTagResponseDto(1L, "JAVA");
        when(skillTagRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(skillTagRepository.findByName("JAVA")).thenReturn(Optional.empty());
        when(skillTagRepository.save(any(SkillTag.class))).thenReturn(entity);
        when(skillTagMapper.toDto(entity)).thenReturn(dto);
        SkillTagResponseDto result = skillTagUpdateService.updateSkillTag(1L, request);
        assertEquals(dto, result);
    }

    @Test
    void updateSkillTag_notFound() {
        SkillTagRequestDto request = new SkillTagRequestDto("JAVA");
        when(skillTagRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> skillTagUpdateService.updateSkillTag(2L, request));
    }

    @Test
    void updateSkillTag_duplicate() {
        SkillTagRequestDto request = new SkillTagRequestDto("JAVA");
        SkillTag entity = SkillTag.builder().id(1L).name("JAVA").build();
        SkillTag other = SkillTag.builder().id(2L).name("JAVA").build();
        when(skillTagRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(skillTagRepository.findByName("JAVA")).thenReturn(Optional.of(other));
        assertThrows(SkillTagAlreadyExistsException.class, () -> skillTagUpdateService.updateSkillTag(1L, request));
    }
}
