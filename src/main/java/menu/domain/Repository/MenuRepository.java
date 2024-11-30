package menu.domain.Repository;

import java.util.ArrayList;
import java.util.List;
import menu.domain.Menu;

public class MenuRepository {

    private MenuRepository() {
    }

    private static final List<Menu> menus = new ArrayList<>();

    public static void add(Menu menu) {
        menus.add(menu);
    }

    public static Menu findMenuByName(String name) {
        return menus.stream()
                .filter(menu -> menu.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 메뉴"));
    }
}
