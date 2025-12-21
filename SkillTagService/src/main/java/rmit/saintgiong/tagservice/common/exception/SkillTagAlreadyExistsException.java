package rmit.saintgiong.tagservice.common.exception;

import lombok.Getter;
import rmit.saintgiong.tagapi.internal.dto.SkillTagResponseDto;

@Getter
public class SkillTagAlreadyExistsException extends RuntimeException {

    private final SkillTagResponseDto existingSkillTag;

    public SkillTagAlreadyExistsException(String message, SkillTagResponseDto existingSkillTag) {
        super(message);
        this.existingSkillTag = existingSkillTag;
    }
}
