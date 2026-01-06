package rmit.saintgiong.tagservice.domain.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import rmit.saintgiong.tagapi.internal.common.dto.SkillTagResponseDto;
import rmit.saintgiong.tagservice.domain.entity.SkillTag;
import rmit.saintgiong.tagservice.domain.mapper.SkillTagMapper;
import rmit.saintgiong.tagservice.domain.repository.SkillTagRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SkillTagGetServiceTest {
    @Mock
    private SkillTagRepository skillTagRepository;
    @Mock
    private SkillTagMapper skillTagMapper;
    @InjectMocks
    private SkillTagGetService skillTagGetService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getSkillTagById_found() {
        SkillTag tag = SkillTag.builder().id(1L).name("JAVA").build();
        SkillTagResponseDto dto = new SkillTagResponseDto(1L, "JAVA");
        when(skillTagRepository.findById(1L)).thenReturn(Optional.of(tag));
        when(skillTagMapper.toDto(tag)).thenReturn(dto);
        SkillTagResponseDto result = skillTagGetService.getSkillTagById(1L);
        assertEquals(dto, result);
    }

    @Test
    void getSkillTagById_notFound() {
        when(skillTagRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> skillTagGetService.getSkillTagById(2L));
    }

    @Test
    void getAllSkillTags_paged() {
        SkillTag tag = SkillTag.builder().id(1L).name("JAVA").build();
        SkillTagResponseDto dto = new SkillTagResponseDto(1L, "JAVA");
        Pageable pageable = PageRequest.of(0, 10);
        Page<SkillTag> page = new PageImpl<>(Collections.singletonList(tag));
        when(skillTagRepository.findAll(pageable)).thenReturn(page);
        when(skillTagMapper.toDto(tag)).thenReturn(dto);
        Page<SkillTagResponseDto> result = skillTagGetService.getAllSkillTags(pageable);
        assertEquals(1, result.getTotalElements());
        assertEquals(dto, result.getContent().get(0));
    }

    @Test
    void autocompleteSkillTags() {
        SkillTag tag1 = SkillTag.builder().id(1L).name("JAVA").build();
        SkillTag tag2 = SkillTag.builder().id(2L).name("JAVASCRIPT").build();
        SkillTagResponseDto dto1 = new SkillTagResponseDto(1L, "JAVA");
        SkillTagResponseDto dto2 = new SkillTagResponseDto(2L, "JAVASCRIPT");
        when(skillTagRepository.findByNameContainingIgnoreCase("java")).thenReturn(Arrays.asList(tag1, tag2));
        when(skillTagMapper.toDto(tag1)).thenReturn(dto1);
        when(skillTagMapper.toDto(tag2)).thenReturn(dto2);
        List<SkillTagResponseDto> result = skillTagGetService.autocompleteSkillTags("java");
        assertEquals(2, result.size());
        assertTrue(result.contains(dto1));
        assertTrue(result.contains(dto2));
    }
}
