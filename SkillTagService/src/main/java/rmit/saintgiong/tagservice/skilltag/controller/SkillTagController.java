package rmit.saintgiong.tagservice.skilltag.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.saintgiong.tagapi.internal.dto.SkillTagRequestDto;
import rmit.saintgiong.tagapi.internal.dto.SkillTagResponseDto;
import rmit.saintgiong.tagapi.internal.dto.common.ApiResponseDto;
import rmit.saintgiong.tagservice.skilltag.services.CreateSkillTagService;
import rmit.saintgiong.tagservice.skilltag.services.DeleteSkillTagService;
import rmit.saintgiong.tagservice.skilltag.services.GetSkillTagService;
import rmit.saintgiong.tagservice.skilltag.services.UpdateSkillTagService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Skill Tag", description = "APIs for managing system Skill tags")
public class SkillTagController {

    private final CreateSkillTagService createSkillTagService;
    private final GetSkillTagService getSkillTagService;
    private final UpdateSkillTagService updateSkillTagService;
    private final DeleteSkillTagService deleteSkillTagService;

    @PostMapping("/create")
    @Operation(summary = "Create a new skill tag", description = "Creates a new skill tag with the provided information")
    public ResponseEntity<ApiResponseDto<SkillTagResponseDto>> createSkillTag(
            @Valid @RequestBody SkillTagRequestDto request) {
        SkillTagResponseDto createdTag = createSkillTagService.createSkillTag(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseDto.success(createdTag, "Skill tag created successfully"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get skill tag by ID", description = "Retrieves a skill tag by its unique identifier")
    public ResponseEntity<ApiResponseDto<SkillTagResponseDto>> getSkillTagById(
            @Parameter(description = "Skill tag ID") @PathVariable Long id) {
        SkillTagResponseDto skillTag = getSkillTagService.getSkillTagById(id);
        return ResponseEntity.ok(ApiResponseDto.success(skillTag));
    }

    @GetMapping("/all")
    @Operation(summary = "Get all skill tags", description = "Retrieves all skill tags in the system")
    public ResponseEntity<ApiResponseDto<List<SkillTagResponseDto>>> getAllSkillTags() {
        List<SkillTagResponseDto> skillTags = getSkillTagService.getAllSkillTags();
        return ResponseEntity.ok(ApiResponseDto.success(skillTags));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a skill tag", description = "Updates an existing skill tag with the provided information")
    public ResponseEntity<ApiResponseDto<SkillTagResponseDto>> updateSkillTag(
            @Parameter(description = "Skill tag ID") @PathVariable Long id,
            @Valid @RequestBody SkillTagRequestDto request) {
        SkillTagResponseDto updatedTag = updateSkillTagService.updateSkillTag(id, request);
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

