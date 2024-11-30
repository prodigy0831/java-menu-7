package menu.domain.util.validator;

public class CoachNameValidator extends Validator {
    @Override
    public void validate(String input) throws IllegalArgumentException {
        validateNameLength(input);
    }

    private void validateNameLength(String input) {
        if (input.length() < 2 || input.length() > 4) {
            throw new IllegalArgumentException("코치 이름의 길이는 2에서 5사이여야 합니다.");
        }
    }
}
