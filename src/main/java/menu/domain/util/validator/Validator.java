package menu.domain.util.validator;

public abstract class Validator {
    protected abstract void validate(String input) throws IllegalArgumentException;
}
