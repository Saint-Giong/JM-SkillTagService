package rmit.saintgiong.tagservice.skilltag.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import rmit.saintgiong.tagapi.internal.dto.SkillTagDto;
import rmit.saintgiong.tagapi.internal.dto.CreateSkillTagRequestDto;
import rmit.saintgiong.tagservice.skilltag.entity.SkillTag;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SkillTagMapper {

    SkillTagDto toDto(SkillTag entity);

    List<SkillTagDto> toDtoList(List<SkillTag> entities);

    @Mapping(target = "id", ignore = true)
    SkillTag toEntity(CreateSkillTagRequestDto dto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(CreateSkillTagRequestDto dto, @MappingTarget SkillTag entity);
}
