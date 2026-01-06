package rmit.saintgiong.tagservice.domain.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.saintgiong.tagapi.internal.common.dto.SkillTagRequestDto;
import rmit.saintgiong.tagapi.internal.common.dto.SkillTagResponseDto;
import rmit.saintgiong.tagapi.internal.common.dto.api.ApiResponseDto;
import rmit.saintgiong.tagapi.internal.service.InternalCreateSkillTagInterface;
import rmit.saintgiong.tagapi.internal.service.InternalDeleteSkillTagInterface;
import rmit.saintgiong.tagapi.internal.service.InternalGetSkillTagInterface;
import rmit.saintgiong.tagapi.internal.service.InternalUpdateSkillTagInterface;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Skill Tag", description = "APIs for managing system Skill tags")
public class SkillTagController {

    private final InternalCreateSkillTagInterface skillTagCreateService;
    private final InternalGetSkillTagInterface skillTagGetService;
    private final InternalUpdateSkillTagInterface skillTagUpdateService;
    private final InternalDeleteSkillTagInterface deleteSkillTagService;

    @PostMapping("/create")
    @Operation(summary = "Create a new skill tag", description = "Creates a new skill tag with the provided information")
    public ResponseEntity<ApiResponseDto<SkillTagResponseDto>> createSkillTag(
            @Valid @RequestBody SkillTagRequestDto request) {
        SkillTagResponseDto createdTag = skillTagCreateService.createSkillTag(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseDto.success(createdTag, "Skill tag created successfully"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get skill tag by ID", description = "Retrieves a skill tag by its unique identifier")
    public ResponseEntity<ApiResponseDto<SkillTagResponseDto>> getSkillTagById(
            @Parameter(description = "Skill tag ID") @PathVariable Long id) {
        SkillTagResponseDto skillTag = skillTagGetService.getSkillTagById(id);
        return ResponseEntity.ok(ApiResponseDto.success(skillTag));
    }

    @GetMapping("/")
    @Operation(summary = "Get all skill tags", description = "Retrieves all skill tags in the system with pagination")
    public ResponseEntity<ApiResponseDto<Page<SkillTagResponseDto>>> getAllSkillTags(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SkillTagResponseDto> skillTags = skillTagGetService.getAllSkillTags(pageable);
        return ResponseEntity.ok(ApiResponseDto.success(skillTags));
    }

    @GetMapping("/search")
    @Operation(summary = "Autocomplete skill tag search", description = "Returns skill tags contain the given keyword (case-insensitive)")
    public ResponseEntity<ApiResponseDto<List<SkillTagResponseDto>>> autocompleteSkillTags(
            @RequestParam String prefix) {
        List<SkillTagResponseDto> results = skillTagGetService.autocompleteSkillTags(prefix);
        return ResponseEntity.ok(ApiResponseDto.success(results));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a skill tag", description = "Updates an existing skill tag with the provided information")
    public ResponseEntity<ApiResponseDto<SkillTagResponseDto>> updateSkillTag(
            @Parameter(description = "Skill tag ID") @PathVariable Long id,
            @Valid @RequestBody SkillTagRequestDto request) {
        SkillTagResponseDto updatedTag = skillTagUpdateService.updateSkillTag(id, request);
        return ResponseEntity.ok(ApiResponseDto.success(updatedTag, "Skill tag updated successfully"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a skill tag", description = "Deletes a skill tag by its unique identifier")
    public ResponseEntity<ApiResponseDto<Void>> deleteSkillTag(
            @Parameter(description = "Skill tag ID") @PathVariable Long id) {
        deleteSkillTagService.deleteSkillTag(id);
        return ResponseEntity.ok(ApiResponseDto.success(null, "Skill tag deleted successfully"));
    }
}

