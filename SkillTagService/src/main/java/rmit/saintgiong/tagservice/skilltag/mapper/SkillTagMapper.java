package rmit.saintgiong.tagservice.skilltag.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import rmit.saintgiong.tagapi.internal.dto.SkillTagResponseDto;
import rmit.saintgiong.tagapi.internal.dto.SkillTagRequestDto;
import rmit.saintgiong.tagservice.skilltag.entity.SkillTag;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SkillTagMapper {

    SkillTagResponseDto toDto(SkillTag entity);

    List<SkillTagResponseDto> toDtoList(List<SkillTag> entities);

    @Mapping(target = "id", ignore = true)
    SkillTag toEntity(SkillTagRequestDto dto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(SkillTagRequestDto dto, @MappingTarget SkillTag entity);
}
