package rmit.saintgiong.tagservice.common.exception.resources;

import lombok.Getter;
import rmit.saintgiong.tagapi.internal.common.dto.SkillTagResponseDto;

@Getter
public class SkillTagAlreadyExistsException extends RuntimeException {

    private final SkillTagResponseDto existingSkillTag;

    public SkillTagAlreadyExistsException(String message, SkillTagResponseDto existingSkillTag) {
        super(message);
        this.existingSkillTag = existingSkillTag;
    }
}
