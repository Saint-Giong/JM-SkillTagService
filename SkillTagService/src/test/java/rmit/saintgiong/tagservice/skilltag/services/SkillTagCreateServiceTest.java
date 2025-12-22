package rmit.saintgiong.tagservice.skilltag.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rmit.saintgiong.tagapi.internal.dto.SkillTagRequestDto;
import rmit.saintgiong.tagapi.internal.dto.SkillTagResponseDto;
import rmit.saintgiong.tagservice.common.exception.SkillTagAlreadyExistsException;
import rmit.saintgiong.tagservice.skilltag.entity.SkillTag;
import rmit.saintgiong.tagservice.skilltag.mapper.SkillTagMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SkillTagCreateServiceTest {
    @Mock
    private SkillTagRepository skillTagRepository;
    @Mock
    private SkillTagMapper skillTagMapper;
    @InjectMocks
    private SkillTagCreateService skillTagCreateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createSkillTag_success() {
        SkillTagRequestDto request = new SkillTagRequestDto("JAVA");
        SkillTag entity = SkillTag.builder().id(1L).name("JAVA").build();
        SkillTagResponseDto dto = new SkillTagResponseDto(1L, "JAVA");
        when(skillTagRepository.findByNameIgnoreCase("JAVA")).thenReturn(Optional.empty());
        when(skillTagMapper.toEntity(request)).thenReturn(entity);
        when(skillTagRepository.save(any(SkillTag.class))).thenReturn(entity);
        when(skillTagMapper.toDto(entity)).thenReturn(dto);
        SkillTagResponseDto result = skillTagCreateService.createSkillTag(request);
        assertEquals(dto, result);
    }

    @Test
    void createSkillTag_duplicate() {
        SkillTagRequestDto request = new SkillTagRequestDto("JAVA");
        SkillTag entity = SkillTag.builder().id(1L).name("JAVA").build();
        when(skillTagRepository.findByNameIgnoreCase("JAVA")).thenReturn(Optional.of(entity));
        assertThrows(SkillTagAlreadyExistsException.class, () -> skillTagCreateService.createSkillTag(request));
    }
}
