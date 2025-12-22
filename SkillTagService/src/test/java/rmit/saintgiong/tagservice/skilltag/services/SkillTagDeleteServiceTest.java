package rmit.saintgiong.tagservice.skilltag.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SkillTagDeleteServiceTest {
    @Mock
    private SkillTagRepository skillTagRepository;
    @InjectMocks
    private SkillTagDeleteService skillTagDeleteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deleteSkillTag_success() {
        when(skillTagRepository.existsById(1L)).thenReturn(true);
        doNothing().when(skillTagRepository).deleteById(1L);
        assertDoesNotThrow(() -> skillTagDeleteService.deleteSkillTag(1L));
        verify(skillTagRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteSkillTag_notFound() {
        when(skillTagRepository.existsById(2L)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> skillTagDeleteService.deleteSkillTag(2L));
    }
}
