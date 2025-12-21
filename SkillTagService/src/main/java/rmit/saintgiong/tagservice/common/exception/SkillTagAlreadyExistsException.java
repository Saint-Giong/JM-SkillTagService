package rmit.saintgiong.tagservice.common.exception;

import lombok.Getter;
import rmit.saintgiong.tagapi.internal.dto.SkillTagDto;

@Getter
public class SkillTagAlreadyExistsException extends RuntimeException {

    private final SkillTagDto existingSkillTag;

    public SkillTagAlreadyExistsException(String message, SkillTagDto existingSkillTag) {
        super(message);
        this.existingSkillTag = existingSkillTag;
    }
}
