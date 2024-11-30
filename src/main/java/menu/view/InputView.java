package menu.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.stream.Collectors;
import menu.domain.Coach;
import menu.domain.Menu;
import menu.domain.Repository.MenuRepository;
import menu.domain.util.Util;
import menu.domain.util.validator.CoachNameValidator;

public class InputView {

    private static final InputView instance = new InputView();

    public static InputView getInstance() {
        return instance;
    }

    private InputView() {
    }

    // static이면 이 위에 지우고 아래를 static으로 만들면됨

    public int readBudget() {
        System.out.println(Message.INPUT_BUDGET.message);
        String input = Console.readLine();
        // String input = Util.removeSpace(Console.readLine());
        // validate
        return Integer.parseInt(input);
    }

    public List<Coach> readCoachNames() {
        try {
            System.out.println("\n코치의 이름을 입력해 주세요. (, 로 구분)");
            List<String> coaches = Util.splitByComma(Console.readLine());
            validateCoachNumber(coaches);
            coaches.forEach(coach -> new CoachNameValidator().validate(coach));
            return convertToCoach(coaches);
        } catch (IllegalArgumentException e) {
            printExceptionMessage(e);
            return readCoachNames();
        }
    }

    private List<Coach> convertToCoach(List<String> coaches) {
        return coaches.stream().map(Coach::new).collect(Collectors.toList());
    }

    private void validateCoachNumber(List<String> coaches) {
        if (coaches.size() > 5 || coaches.size() < 2) {
            throw new IllegalArgumentException("코치의 수는 2명에서 5명 사이여야 합니다.");
        }
    }

    private void printExceptionMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    public List<Menu> readMenuNotToEat(String name) {
        try {
            System.out.println(name + "(이)가 못 먹는 메뉴를 입력해 주세요.");
            String input = Util.removeSpace(Console.readLine());
            if (input.isBlank()) {
                return List.of();
            }
            List<Menu> menus = formatMenus(input);
            validateMenuNotToEatSize(menus);
            return menus;
        } catch (IllegalArgumentException e) {
            printExceptionMessage(e);
            return readMenuNotToEat(name);
        }
    }

    private void validateMenuNotToEatSize(List<Menu> menus) {
        if (menus.size() > 2) {
            throw new IllegalArgumentException("먹지 못하는 메뉴는 2개 이하여야 합니다.");
        }
    }

    private List<Menu> formatMenus(String input) {
        return Util.splitByComma(input)
                .stream().map(MenuRepository::findMenuByName)
                .collect(Collectors.toList());
    }


    private enum Message {
        INPUT_BUDGET("구입금액을 입력해 주세요.");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }
}